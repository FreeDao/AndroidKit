package com.hh.kit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <dl>
 * <dt>数据库初始化</dt>
 * <dd></dd>
 * </dl>
 */
public class CitySQLiteHelper extends SQLiteOpenHelper {

	/**
	 * 构造
	 * 
	 * @param context
	 */
	public CitySQLiteHelper(Context context) {
		super(context, ConstSQLite.CITY_DB, null, ConstSQLite.VERSION_DB);

	}

	/**
	 * <dt>功能：创建数据库</dt> <dt>作者： danielyip</dt>
	 * 
	 * @param db
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.beginTransaction();

		StringBuilder sb = new StringBuilder();
		sb.setLength(0);

		// 地区表
		sb.append(" CREATE TABLE IF NOT EXISTS " + ConstSQLite.Tbl_Area + "("
				+ "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "areaname NVARCHAR DEFAULT NULL,"
				+ "areaid INTEGER DEFAULT(0),"
				+ "addtime DATETIME  default (datetime('now','localtime'))"
				+ ");");
		db.execSQL(sb.toString());
		sb.setLength(0);

		// 省份表
		sb.append(" CREATE TABLE IF NOT EXISTS " + ConstSQLite.Tbl_Province
				+ "(" + "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "areaid INTEGER NULL default(0),"
				+ "provincecode INTEGER NULL default(0),"
				+ "provincename NVARCHAR DEFAULT NULL,"
				+ "subtitle NVARCHAR DEFAULT NULL,"
				+ "addtime DATETIME  default (datetime('now','localtime'))"
				+ ");");
		db.execSQL(sb.toString());
		sb.setLength(0);

		// 城市表
		sb.append(" CREATE TABLE IF NOT EXISTS " + ConstSQLite.Tbl_City + "("
				+ "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "areaid INTEGER NULL default(0),"
				+ "provinceid INTEGER NULL default(0),"
				+ "citycode INTEGER NULL default(0),"
				+ "cityname NVARCHAR DEFAULT NULL,"
				+ "hotcity NVARCHAR DEFAULT NULL,"
				+ "subtitle NVARCHAR DEFAULT NULL,"
				+ "letter NVARCHAR(10) DEFAULT NULL,"
				+ "addtime DATETIME  default (datetime('now','localtime'))"
				+ ");");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append(" CREATE TABLE IF NOT EXISTS " + ConstSQLite.REGULARLY_CITY
				+ "(" + "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "citycode NVARCHAR DEFAULT NULL,"
				+ "addtime DATETIME  default (datetime('now','localtime'))"
				+ ");");
		db.execSQL(sb.toString());
		sb.setLength(0);

		db.setTransactionSuccessful();
		db.endTransaction();

		initCityData(db);
	}

	/**
	 * <dt>功能：更新数据库结构</dt> <dt>作者： danielyip</dt>
	 * 
	 * @param db
	 * @param oldVersion
	 *            旧版本号
	 * @param newVersion
	 *            现版本号
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		updateDB(db);
	}

	private void updateDB(SQLiteDatabase db) {

		db.beginTransaction();
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append(" CREATE TABLE IF NOT EXISTS " + ConstSQLite.REGULARLY_CITY
				+ "(" + "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "citycode NVARCHAR DEFAULT NULL,"
				+ "addtime DATETIME  default (datetime('now','localtime'))"
				+ ");");
		db.execSQL(sb.toString());
		sb.setLength(0);
		db.setTransactionSuccessful();
		db.endTransaction();
		// db.beginTransaction();
		// StringBuilder sb=new StringBuilder();
		//
		// //海南
		//
		// //
		//
		// db.setTransactionSuccessful();
		// db.endTransaction();
	}

	/**
	 * <dt>功能：删除所有表的数据,并删除数据库</dt> <dt>作者： danielyip</dt>
	 * 
	 * @param context
	 */
	public synchronized void deleteAllTable(Context context) {
		String drop_Tbl_Area = "drop table if exists " + ConstSQLite.Tbl_Area;
		String drop_Tbl_Province = "drop table if exists "
				+ ConstSQLite.Tbl_Province;
		String drop_Tbl_City = "drop table if exists " + ConstSQLite.Tbl_City;

		SQLiteDatabase db = getWritableDatabase();

		db.beginTransaction();

		db.execSQL(drop_Tbl_Area); // "drop table if exists tbl_area";
		db.execSQL(drop_Tbl_Province);// "drop table if exists tbl_province";
		db.execSQL(drop_Tbl_City);// "drop table if exists tbl_city";

		db.setTransactionSuccessful();
		db.endTransaction();

		db.close();

		context.deleteDatabase(ConstSQLite.CITY_DB);
	}

	private void initCityData(SQLiteDatabase db) {
		db.beginTransaction();
		StringBuilder sb = new StringBuilder();

		/*
		 * //初始化省市数据
		 * sb.append("insert into tbl_area (areaname,areaid) values('热门城市: ',1);"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append("insert into tbl_area (areaname,areaid) values('直属城市: ',2);"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		sb.append("insert into tbl_area (areaname,areaid) values('华北东北: ',3);");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_area (areaname,areaid) values('华南华中: ',4);");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_area (areaname,areaid) values('华东地区: ',5);");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_area (areaname,areaid) values('西北西南: ',6);");
		db.execSQL(sb.toString());
		sb.setLength(0);
		/*
		 * sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,110000,'北京','北京');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,310000,'上海','上海');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,120000,'天津','天津');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,610100,'西安','西安');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,510100,'成都','成都');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,330100,'杭州','杭州');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,460100,'海口','海口');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0); sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(1,350200,'厦门','厦门');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		/*
		 * sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(2,110000,'北京','北京');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,hotcity,letter,subtitle) values(1,110000,110000,'北京','true','b','北京');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		/*
		 * sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(2,120000,'天津','天津');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter,subtitle) values(1,120000,120000,'天津','t','天津');");
		db.execSQL(sb.toString());
		sb.setLength(0);
		/*
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120101,'和平区','h');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120102,'河东区','h');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120103,'河西区','h');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120104,'南开区','n');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120105,'河北区','h');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120106,'红桥区','h');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120110,'东丽区','d');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120111,'西青区','x');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120112,'津南区','j');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120113,'北辰区','b');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120114,'武清区','w');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120115,'宝坻区','b');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120116,'滨海新区','b');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120221,'宁河县','n');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120223,'静海县','j');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 * 
		 * sb.append(
		 * "insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(2,120000,120225,'蓟县','j');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */
		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,130000,'河北省','河北');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130100,'石家庄','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130200,'唐山','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130300,'秦皇岛','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130400,'邯郸','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130500,'邢台','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130600,'保定','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130700,'张家口','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130800,'承德','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,130900,'沧州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,131000,'廊坊','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,130000,131100,'衡水','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,610000,'陕西省','陕西');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610100,'西安','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610200,'铜川','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610300,'宝鸡','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610400,'咸阳','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610500,'渭南','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610600,'延安','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610700,'汉中','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610800,'榆林','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,610900,'安康','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,610000,611000,'商洛','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,140000,'山西省','山西');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140100,'太原','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140200,'大同','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140300,'阳泉','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140400,'长治','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140500,'晋城','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140600,'朔州','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140700,'晋中','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140800,'运城','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,140900,'忻州','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,141000,'临汾','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,140000,141100,'吕梁','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,210000,'辽宁省','辽宁');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210100,'沈阳','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210200,'大连','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210300,'鞍山','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210400,'抚顺','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210500,'本溪','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210600,'丹东','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210700,'锦州','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210800,'营口','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,210900,'阜新','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,211000,'辽阳','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,211100,'盘锦','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,211200,'铁岭','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,211300,'朝阳','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,210000,211400,'葫芦岛','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,220000,'吉林省','吉林');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220100,'长春','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220200,'吉林','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220300,'四平','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220400,'辽源','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220500,'通化','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220600,'白山','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220700,'松原','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,220800,'白城','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,220000,222400,'延边朝鲜族自治州','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,230000,'黑龙江省','黑龙江');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230100,'哈尔滨','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230200,'齐齐哈尔','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230300,'鸡西','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230400,'鹤岗','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230500,'双鸭山','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230600,'大庆','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230700,'伊春','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230800,'佳木斯','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,230900,'七台河','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,231000,'牡丹江','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,231100,'黑河','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,231200,'绥化','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,230000,232700,'大兴安岭地区','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(3,150000,'内蒙古','内蒙古');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150100,'呼和浩特','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150200,'包头','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150300,'乌海','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150400,'赤峰','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150500,'通辽','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150600,'鄂尔多斯','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150700,'呼伦贝尔','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150800,'巴彦淖尔','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,150900,'乌兰察布','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,152200,'兴安盟','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,152900,'阿拉善盟','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(3,150000,152500,'锡林郭勒盟','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		/*
		 * sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(2,310000,'上海','上海');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter,subtitle) values(2,310000,310000,'上海','s','上海');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(5,320000,'江苏省','江苏');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,hotcity,letter) values(5,320000,320100,'南京','true','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320200,'无锡','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320300,'徐州','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320400,'常州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320500,'苏州','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320600,'南通','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320700,'连云港','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320800,'淮安','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,320900,'盐城','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,321000,'扬州','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,321100,'镇江','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,321200,'泰州','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,320000,321300,'宿迁','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(5,330000,'浙江省','浙江');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,hotcity,letter) values(5,330000,330100,'杭州','true','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330200,'宁波','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330300,'温州','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330400,'嘉兴','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330500,'湖州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330600,'绍兴','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330700,'金华','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330800,'衢州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,330900,'舟山','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,331000,'台州','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,330000,331100,'丽水','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,'subtitle') values(5,340000,'安徽省','安徽');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340100,'合肥','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340200,'芜湖','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340300,'蚌埠','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340400,'淮南','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340500,'马鞍山','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340600,'淮北','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340700,'铜陵','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,340800,'安庆','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341000,'黄山','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341100,'滁州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341200,'阜阳','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341300,'宿州','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341400,'巢湖','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341500,'六安','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341600,'亳州','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341700,'池州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,340000,341800,'宣城','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(5,350000,'福建省','福建');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350100,'福州','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350200,'厦门','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350300,'莆田','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350400,'三明','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350500,'泉州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350600,'漳州','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350700,'南平','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350800,'龙岩','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,350000,350900,'宁德','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(5,360000,'江西省','江西');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360100,'南昌','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360200,'景德镇','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360300,'萍乡','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360400,'九江','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360500,'新余','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360600,'鹰潭','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360700,'赣州','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360800,'吉安','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,360900,'宜春','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,361000,'抚州','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,360000,361100,'上饶','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(5,370000,'山东省','山东');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370100,'济南','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370200,'青岛','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370300,'淄博','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370400,'枣庄','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370500,'东营','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370600,'烟台','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370700,'潍坊','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370800,'济宁','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,370900,'泰安','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371000,'威海','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371100,'日照','r');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371200,'莱芜','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371300,'临沂','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371400,'德州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371500,'聊城','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371600,'滨州','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(5,370000,371700,'荷泽','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,410000,'河南省','河南');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410100,'郑州','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410200,'开封','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410300,'洛阳','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410400,'平顶山','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410500,'安阳','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410600,'鹤壁','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410700,'新乡','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410800,'焦作','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,410900,'濮阳','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411000,'许昌','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411100,'漯河','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411200,'三门峡','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411300,'南阳','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411400,'商丘','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411500,'信阳','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411600,'周口','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,411700,'驻马店','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,410000,419001,'济源','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,420000,'湖北省','湖北');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420100,'武汉','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420200,'黄石','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420300,'十堰','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420500,'宜昌','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420600,'襄樊','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420700,'鄂州','e');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420800,'荆门','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,420900,'孝感','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,421000,'荆州','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,421100,'黄冈','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,421200,'咸宁','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,421300,'随州','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,422800,'恩施土家族苗族自治州','e');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,429004,'仙桃','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,429005,'潜江','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,429006,'天门','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,421281,'赤壁','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,420000,429021,'神农架林区','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,430000,'湖南省','湖南');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430100,'长沙','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430200,'株洲','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430300,'湘潭','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430400,'衡阳','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430500,'邵阳','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430600,'岳阳','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430700,'常德','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430800,'张家界','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,430900,'益阳','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,431000,'郴州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,431100,'永州','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,431200,'怀化','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,431300,'娄底','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,430000,433100,'湘西土家族苗族自治州	','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,440000,'广东省','广东');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,hotcity,letter,subtitle) values(4,440000,440100,'广州','true','g','广州');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440200,'韶关','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter,subtitle) values(4,440000,440300,'深圳','s','深圳');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440400,'珠海','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440500,'汕头','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440600,'佛山','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440700,'江门','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440800,'湛江','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,440900,'茂名','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441200,'肇庆','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441300,'惠州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441400,'梅州','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441500,'汕尾','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441600,'河源','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441700,'阳江','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441800,'清远','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,441900,'东莞','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,442000,'中山','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,445100,'潮州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,445200,'揭阳','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,440000,445300,'云浮','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,450000,'广西壮族自治区','广西');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450100,'南宁','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450200,'柳州','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450300,'桂林','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450400,'梧州','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450500,'北海','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450600,'防城港','f');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450700,'钦州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450800,'贵港','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,450900,'玉林','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,451000,'百色','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,451100,'贺州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,451200,'河池','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,451300,'来宾','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,450000,451400,'崇左','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(4,460000,'海南省','海南');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,460100,'海口','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,460200,'三亚','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469001,'五指山','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469002,'琼海','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469003,'儋州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469005,'文昌','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469006,'万宁','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469007,'东方','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469021,'定安','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469022,'屯昌','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469023,'澄迈','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469024,'临高','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469025,'白沙','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469026,'昌江','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469027,'乐东','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469028,'陵水','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469029,'保亭','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(4,460000,469030,'琼中','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		/*
		 * sb.append(
		 * "insert into tbl_province (areaid,provincecode,provincename,subtitle) values(2,500000,'重庆','重庆');"
		 * ); db.execSQL(sb.toString()); sb.setLength(0);
		 */

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter,subtitle) values(2,500000,500000,'重庆','c','重庆');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,510000,'四川省','四川');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,hotcity,letter) values(6,510000,510100,'成都','true','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510300,'自贡','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510400,'攀枝花','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510500,'泸州','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510600,'德阳','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510700,'绵阳','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510800,'广元','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,510900,'遂宁','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511000,'内江','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511100,'乐山','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511300,'南充','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511400,'眉山','m');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511500,'宜宾','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511600,'广安','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511700,'达州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511800,'雅安','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,511900,'巴中','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,512000,'资阳','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,513200,'阿坝藏族羌族自治州','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,513300,'甘孜藏族自治州','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,510000,513400,'凉山彝族自治州','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,520000,'贵州省','贵州');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520100,'贵阳','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520200,'六盘水','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520300,'遵义','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520400,'安顺','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520500,'毕节','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,520600,'铜仁','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,522600,'黔东南苗族侗族州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,522700,'黔南布依族苗族州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,520000,522300,'黔西南布依族苗族州','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,530000,'云南省','云南');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530100,'昆明','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530300,'曲靖','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530400,'玉溪','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530500,'保山','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530600,'昭通','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530700,'丽江','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530800,'普洱','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,530900,'临沧','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,532900,'大理白族自治州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,533400,'迪庆藏族自治州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,532300,'楚雄彝族自治州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,533300,'怒江傈僳族自治州','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,532600,'文山壮族苗族自治州','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,532800,'西双版纳傣族自治州','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,533100,'德宏傣族景颇族自治州','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,530000,532500,'红河哈尼族彝族自治州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,540000,'西藏','西藏');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,540100,'拉萨','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542100,'昌都地区','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542200,'山南地区','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542300,'日喀则地区','r');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542400,'那曲地区','n');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542500,'阿里地区','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,540000,542600,'林芝地区','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,620000,'甘肃省','甘肃');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620100,'兰州','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620200,'嘉峪关','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620300,'金昌','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620400,'白银','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620500,'天水','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620600,'武威','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620700,'张掖','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620800,'平凉','p');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,620900,'酒泉','j');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,621000,'庆阳','q');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,621100,'定西','d');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,620000,621200,'陇南','l');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,630000,'青海省','青海');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,630100,'西宁','x');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632100,'海东地','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632200,'海北藏族自治州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632300,'黄南藏族自治州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632500,'海南藏族自治州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632600,'果洛藏族自治州','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632700,'玉树藏族自治州','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,630000,632800,'海西蒙古族藏族自治州','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,640000,'宁夏回族自治区','宁夏');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,640000,640100,'银川','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,640000,640200,'石嘴山','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,640000,640300,'吴忠','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,640000,640400,'固原','g');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,640000,640500,'中卫','z');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_province (areaid,provincecode,provincename,subtitle) values(6,650000,'新疆','新疆');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,650100,'乌鲁木齐','w');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,650200,'克拉玛依','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652100,'吐鲁番地区','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652200,'哈密地区','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652300,'昌吉回族自治州','c');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652700,'博尔塔拉蒙古自治州','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652800,'巴音郭楞蒙古自治州','b');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,652900,'阿克苏地区','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,653000,'克孜勒苏柯尔克孜自治州','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,653100,'喀什地区','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,653200,'和田地区','h');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,654000,'伊犁哈萨克自治州','y');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,654003,'奎屯','k');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,654200,'塔城地区','t');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,654300,'阿勒泰地区','a');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		sb.append("insert into tbl_city (areaid,provinceid,citycode,cityname,letter) values(6,650000,659001,'石河子','s');");
		db.execSQL(sb.toString());
		sb.setLength(0);

		db.setTransactionSuccessful();
		db.endTransaction();

	}

}
