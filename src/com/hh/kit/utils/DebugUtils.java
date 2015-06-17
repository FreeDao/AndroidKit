/**  
 * Copyright © 2013武汉天喻通讯技术有限公司. All rights reserved.
 *
 * @Title: DebugTools.java
 * @Prject: Whcity_ChongQing
 * @Package: com.cmcc.wificity.utils
 * @Description: 处理调试版本和正式版本是否打印Log
 * @author: Mr.Gdp  
 * @date: 2013年9月17日 上午11:36:45
 * @version: V1.0  
 */
package com.hh.kit.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

import android.text.TextUtils;
import android.util.Log;

/**
 * @ClassName: DebugTools
 * @Description: 处理调试版本和正式版本是否打印Log
 * @author: Mr.Gdp
 * @date: 2013年9月17日 上午11:36:45
 */
public class DebugUtils {
	/**
	 * isDebug为true表示为调试版本 ，false表示为正式版本
	 * 
	 * 正式版本将isDebug改为false即可屏蔽Log信息
	 */
	public final static boolean isDebug = true;// 测试控制量 主要用于Log/SYSO

	/**
	 * 根据isDebug判断是否提示System.out
	 */
	public static void showSysO(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			System.out.println("MSG=" + msg);
		}
	}

	/**
	 * 根据isDebug判断是否提示System.out
	 */
	public static void showSysO(String msg, Throwable tr) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			System.out.println("MSG=" + msg + '\n' + getStackTraceString(tr));
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.d
	 */
	public static void showLogD(String msg) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			Log.d("DEBUG", msg);
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.d
	 */
	public static void showLogD(String tag, String msg) {
		if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.d
	 */
	public static void showLogD(String tag, String msg, Throwable tr) {
		if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			Log.d(tag, msg + '\n' + getStackTraceString(tr));
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.e
	 */
	public static void showLogE(String msg) {
		if (null == msg || "".equals(msg)) {
			return;
		}
		if (isDebug) {
			Log.e("ERROR", msg);
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.e
	 */
	public static void showLogE(String tag, String msg) {
		if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	/**
	 * 根据isDebug判断是否提示 Log.e
	 */
	public static void showLogE(String tag, String msg, Throwable tr) {
		if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(msg)) {
			return;
		}
		if (isDebug) {
			Log.e(tag, msg + '\n' + getStackTraceString(tr));
		}
	}

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 * 
	 * @param tr
	 *            An exception to log
	 */
	public static String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}

		// This is to reduce the amount of log spew that apps do in the
		// non-error
		// condition of the network being unavailable.
		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();
		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);
		return sw.toString();
	}
}
