package com.hh.kit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName: PreferencesUtils
 * @Description: 存放键值对
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午3:25:39
 */
public class PreferencesUtils {
	private final static String FILENAME = "base_config";
	private SharedPreferences mSharedPreferences;
	private static PreferencesUtils mInstance;
	@SuppressWarnings("deprecation")
	private final static int[] MODES = new int[] {
	/**
	 * 默认操作模式，代表该文件是私有数据，只能被应用本身访问， 在该模式下，写入的内容会覆盖原文件的内容，
	 * 如果想把新写入的内容追加到原文件中，可以使用Context.MODE_APPEND
	 **/
	Context.MODE_PRIVATE,

	/** 表示当前文件可以被其他应用读取 **/
	Context.MODE_WORLD_READABLE,

	/** 表示当前文件可以被其他应用写入 **/
	Context.MODE_WORLD_WRITEABLE,
	/**
	 * 如果希望文件被其他应用读和写， 可以传入:Context.MODE_WORLD_READABLE+Context.
	 * MODE_WORLD_WRITEABLE
	 **/
	/** 该模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件 **/
	Context.MODE_APPEND };

	public PreferencesUtils(Context context) {
		mSharedPreferences = context.getSharedPreferences(FILENAME, MODES[1]);
	}

	public static final void init(final Context context) {
		if (null == mInstance) {
			mInstance = new PreferencesUtils(context);
		}
	}

	public static final PreferencesUtils getInstance() {
		if (null == mInstance) {
			throw new IllegalArgumentException(
					"You must call init() method before call getInstance()");
		}
		return mInstance;
	}

	public SharedPreferences getPreferences() {
		return mSharedPreferences;
	}

	/**
	 * 获取对应键的字符串值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public String getStringValue(String key, String defaultValue) {
		return mSharedPreferences.getString(key, defaultValue);
	}

	/**
	 * 设置对应键的字符串值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setStringValue(String key, String value) {
		mSharedPreferences.edit().putString(key, value).commit();
	}

	/**
	 * 获取对应键的xml字符串值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public String getSettingXmlStr(String key, String defaultValue) {
		return mSharedPreferences.getString(key, defaultValue).replace("&amp;",
				"&");
	}

	/**
	 * 获取对应键的整数值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public int getIntValue(String key, int defaultValue) {
		return mSharedPreferences.getInt(key, defaultValue);
	}

	/**
	 * 设置对应键的整数值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void SetSettingInt(String key, int value) {
		mSharedPreferences.edit().putInt(key, value).commit();
	}

	/**
	 * 获取对应键的长整型值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public Long getLongValue(String key, long defaultValue) {
		return mSharedPreferences.getLong(key, defaultValue);
	}

	/**
	 * 设置对应键的成整型值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setLongValue(String key, Long value) {
		mSharedPreferences.edit().putLong(key, value).commit();
	}

	/**
	 * 获取对应键的boolean值
	 * 
	 * @param key
	 *            键
	 * @param defaultValue
	 *            默认值
	 * @return 值
	 */
	public Boolean getBooleanValue(String key, boolean defaultValue) {
		return mSharedPreferences.getBoolean(key, defaultValue);
	}

	/**
	 * 设置对应键的boolean值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setBooleanValue(String key, boolean value) {
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}

}
