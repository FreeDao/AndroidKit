/**  
 * Copyright © 2013 武汉天喻通讯技术有限公司. All rights reserved.
 *
 * @Title: CommonActivity.java
 * @Description: TODO
 * @author: Mr.Gdp  
 * @date: 2013年9月22日 下午4:43:06
 */
package com.hh.kit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hh.kit.utils.DebugUtils;

/**
 * @ClassName: CommonActivity
 * @Description: 基础Activity 便于管理
 * @author: Mr.Gdp
 * @date: 2013年9月22日 下午4:43:06
 */

public class CommonActivity extends FragmentActivity {

	private static final String TAG = CommonActivity.class.getSimpleName();
	private ProgressDialog mProgressDialog;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void showDialog() {
		this.showDialog(null);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
	}

	/**
	 * 显示加载提示框
	 */
	public void showDialog(CharSequence title) {
		if (null == title) {
			title = getResources().getString(R.string.commondialog_loadingtxt);
		}
		if (mProgressDialog != null && mProgressDialog.isShowing())
			return;
		try {
			mProgressDialog = ProgressDialog.show(this, null, title);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		} catch (Exception e) {
			DebugUtils.showLogD(TAG, "progressDialog error", e);
		}
	}

	/**
	 * 关闭加载提示框
	 */
	public void dismissDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing())
			mProgressDialog.dismiss();
	}

}
