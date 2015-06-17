package com.hh.kit.hotsearch;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class HotSearchDataBase {

	private static HotSearchDataBase mInstance = null;
	private static SQLiteDatabase mDatabase = null;
	public static final String DATABASE_FILE = "GyptDatabase.db";
	private static final int DATABASE_VERSION = 1;
	private static final int TABLE_RECOMMEND = 0;
	public static final String mTableNames[] = { "historyseach" };
	// column id strings for "_id" which can be used by any table
	private static final String ID_COL = "_id";
	private static final String[] ID_PROJECTION = new String[] { "_id" };

	// column id strings for "recommend" table
	private final Object mRecommendLock = new Object();
	private static final String RECOMMEND_NAME = "name";
	private static final String RECOMMEND_USERID = "userid";
	private static final String RECOMMEND_TIME = "time";

	public static synchronized HotSearchDataBase getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new HotSearchDataBase();
			try {
				mDatabase = context
						.openOrCreateDatabase(DATABASE_FILE, 0, null);
			} catch (SQLiteException e) {
				// try again by deleting the old db and create a new one
				if (context.deleteDatabase(DATABASE_FILE)) {
					mDatabase = context.openOrCreateDatabase(DATABASE_FILE, 0,
							null);
				}
			}
			// mDatabase should not be null,
			// the only case is RequestAPI test has problem to create db
			if (mDatabase != null && mDatabase.getVersion() != DATABASE_VERSION) {
				mDatabase.beginTransaction();
				try {
					// mDatabase.setVersion(DATABASE_VERSION);
					upgradeDatabase();
					mDatabase.setTransactionSuccessful();
				} finally {
					mDatabase.endTransaction();
				}
			}

			if (mDatabase != null) {
				// use per table Mutex lock, turn off database lock, this
				// improves performance as database's ReentrantLock is expansive
				mDatabase.setLockingEnabled(false);
			}
		}
		return mInstance;
	}

	private static void upgradeDatabase() {
		int oldVersion = mDatabase.getVersion();
		System.out.println("---------oldVersion--------" + oldVersion);
		createTable();
		mDatabase.setVersion(DATABASE_VERSION);

	}

	public boolean hasRecommendTable() {
		synchronized (mRecommendLock) {

			return hasEntries(TABLE_RECOMMEND);
		}
	}

	private static void createTable() {
		System.out.println("----------createTable------");
		mDatabase.execSQL("CREATE TABLE " + mTableNames[TABLE_RECOMMEND] + " ("
				+ ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ RECOMMEND_NAME + " TEXT, " + RECOMMEND_USERID + " TEXT, "
				+ RECOMMEND_TIME + " TEXT " + ")");
	}

	private boolean hasEntries(int tableId) {
		if (mDatabase == null) {
			return false;
		}

		Cursor cursor = null;
		boolean ret = false;
		try {
			cursor = mDatabase.query(mTableNames[tableId], ID_PROJECTION, null,
					null, null, null, null);
			ret = cursor.moveToFirst() == true;
		} catch (IllegalStateException e) {
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return ret;
	}

	// 添加状态
	public void addHistorySearch(HotSearchBean bean) {
		if (bean == null || mDatabase == null) {
			return;
		}
		synchronized (mRecommendLock) {
			try {
				final ContentValues c = new ContentValues();
				c.put(RECOMMEND_NAME, bean.getName());
				c.put(RECOMMEND_USERID, bean.getUserid());
				c.put(RECOMMEND_TIME, bean.getTime());
				mDatabase.insert(mTableNames[TABLE_RECOMMEND], null, c);
			} catch (Exception e) {
			}
		}
	}

	// 查询所有栏目
	public ArrayList<HotSearchBean> getRecommendState() {
		if (mDatabase == null) {
			return null;
		}
		ArrayList<HotSearchBean> ret = new ArrayList<HotSearchBean>();
		synchronized (mRecommendLock) {
			Cursor cursor = null;
			try {
				/**
				 * select top m * from table_name order by column_name desc
				 */
				cursor = mDatabase.rawQuery("SELECT * FROM "
						+ mTableNames[TABLE_RECOMMEND] + (" order by  ")
						+ ID_COL + " desc  limit 0 , 20 ", null);
				if (cursor.moveToFirst()) {
					do {
						HotSearchBean bean = new HotSearchBean();
						bean.setName(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_NAME)));
						bean.setUserid(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_USERID)));
						bean.setTime(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_TIME)));
						ret.add(bean);
					} while (cursor.moveToNext());
				}
			} catch (IllegalStateException e) {
			} finally {
				if (cursor != null)
					cursor.close();
			}
			return ret;
		}
	}

	// 查询所有栏目 %放在条件里面
	public ArrayList<HotSearchBean> getRecommendState(String name) {
		if (mDatabase == null) {
			return null;
		}
		if (name == null) {
			return null;
		}
		ArrayList<HotSearchBean> ret = new ArrayList<HotSearchBean>();
		synchronized (mRecommendLock) {
			Cursor cursor = null;
			try {
				/**
				 * select top m * from table_name order by column_name desc
				 */
				cursor = mDatabase.rawQuery("SELECT * FROM "
						+ mTableNames[TABLE_RECOMMEND] + " where "
						+ RECOMMEND_NAME + " like ? " + (" order by  ")
						+ ID_COL + " desc  limit 0 , 20 ", new String[] { "%"
						+ name + "%" });
				if (cursor.moveToFirst()) {
					do {
						HotSearchBean bean = new HotSearchBean();
						bean.setName(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_NAME)));
						bean.setUserid(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_USERID)));
						bean.setTime(cursor.getString(cursor
								.getColumnIndex(RECOMMEND_TIME)));
						ret.add(bean);
					} while (cursor.moveToNext());
				}
			} catch (IllegalStateException e) {
			} finally {
				if (cursor != null)
					cursor.close();
			}
			return ret;
		}
	}
}
