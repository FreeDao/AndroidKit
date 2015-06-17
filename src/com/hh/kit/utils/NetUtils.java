package com.hh.kit.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ClassName: NetUtils
 * @Description: 联网管理类
 * @author: Mr.Gdp
 * @date: 2013年11月8日 上午10:38:25
 */
public class NetUtils {

	/** 没有网络 **/
	public final static int NET_STATUS_NONE = -1;// 没有网络
	/** WIFI网络 **/
	public final static int NET_STATUS_WIFI = 1;// WIFI网络
	/** 手机网络 **/
	public final static int NET_STATUS_MOBILE = 0;// 手机网络

	/**
	 * info.getSubtype() 这里使用 getSubtype()，不是getType()
	 * getType()返回的是0，或者1，是区分是手机网络还是WIFI。
	 * 
	 * info.getSubtype()取值列表如下：
	 * 
	 * NETWORK_TYPE_CDMA 网络类型为CDMA NETWORK_TYPE_EDGE 网络类型为EDGE
	 * NETWORK_TYPE_EVDO_0 网络类型为EVDO0 NETWORK_TYPE_EVDO_A 网络类型为EVDOA
	 * NETWORK_TYPE_GPRS 网络类型为GPRS NETWORK_TYPE_HSDPA 网络类型为HSDPA
	 * NETWORK_TYPE_HSPA 网络类型为HSPA NETWORK_TYPE_HSUPA 网络类型为HSUPA
	 * NETWORK_TYPE_UMTS 网络类型为UMTS
	 * 
	 * 联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA，电信的3G为EVDO
	 * 
	 * @return 0 手机网络, 1 WIFI ,其余没有网络
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		int flag = NET_STATUS_NONE;
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
							flag = NET_STATUS_WIFI;
						} else {
							flag = NET_STATUS_MOBILE;
						}
					}
					if (flag > NET_STATUS_NONE) {
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * @Title: setNet
	 * @Description: 调用系统设置 设置网络
	 * @param context
	 */
	public static void setNet(Context context) {
		Intent intent = null;
		// 判断手机系统的版本 即API大于10 就是3.0或以上版本
		if (android.os.Build.VERSION.SDK_INT > 10) {
			intent = new Intent(
					android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		} else {
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings",
					"com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		context.startActivity(intent);
		((Activity) context).finish();
	}
}
