/**  
 * Copyright © 2013武汉天喻通讯技术有限公司. All rights reserved.
 *
 * @Title: HomeLanucherActivity.java
 * @Prject: Kit
 * @Package: com.hh.kit
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午1:54:46
 * @version: V1.0  
 */
package com.hh.kit.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hh.kit.CommonActivity;
import com.hh.kit.R;
import com.hh.kit.utils.DebugUtils;
import com.hh.kit.utils.DialogUtils;
import com.hh.kit.utils.DialogUtils.DialogListener;
import com.hh.kit.utils.NetUtils;
import com.hh.kit.utils.PreferencesConfig;
import com.hh.kit.utils.PreferencesUtils;

/**
 * @ClassName: HomeLanucherActivity
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午1:54:46
 */
public class HomeLanucherActivity extends CommonActivity {
	private Context mContext;
	private ProgressBar progressBar;

	private final static int totalTime = 3000;
	private final static int interval = 20;
	private Button btn_goHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_loading);
		mContext = this;
		initView();
		startTimer();
	}

	private void initView() {
		btn_goHome = (Button) findViewById(R.id.gotoHome);
		progressBar = (ProgressBar) findViewById(R.id.progress_horizontal);
		progressBar.setVisibility(View.GONE);
		progressBar.setMax(totalTime);
		btn_goHome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (NetUtils.NET_STATUS_MOBILE == NetUtils
						.isNetworkAvailable(mContext)) {
					showNetDialog();
				} else if (NetUtils.NET_STATUS_WIFI == NetUtils
						.isNetworkAvailable(mContext)) {
					PreferencesUtils.getInstance().setBooleanValue(
							PreferencesConfig.PIC_MODEL, true);
					goHome();
				} else {
					NetUtils.setNet(mContext);
					DebugUtils.showLogD("NetStatus="
							+ NetUtils.isNetworkAvailable(mContext));
				}
			}
		});
	}

	/**
	 * @Title: startTimer
	 * @Description: TODO
	 */
	private void startTimer() {
		// TODO Auto-generated method stub
		new CountDownTimer(totalTime, interval) {
			@Override
			public void onTick(long millisUntilFinished) {
				progressBar
						.setProgress((int) (totalTime - millisUntilFinished));
			}

			@Override
			public void onFinish() {
				progressBar.setProgress(totalTime);
			}
		}.start();
	}

	private void goHome() {
		Intent i = new Intent(mContext, HomeMainActivity.class);
		startActivity(i);
		finish();
	}

	private void showNetDialog() {
		String ms = "亲，您正在使用移动数据网络，会产生流量费用，建议使用wifi网络。";
		DialogUtils.showDialogThreeButton(mContext, ms, "提示", "打开wifi", "只看文字",
				"图文显示", new DialogListener() {
					@Override
					public void onClick(Dialog dialog) {
						// TODO Auto-generated method stub
						NetUtils.setNet(mContext);
					}
				}, new DialogListener() {
					@Override
					public void onClick(Dialog dialog) {
						// TODO Auto-generated method stub
						PreferencesUtils.getInstance().setBooleanValue(
								PreferencesConfig.PIC_MODEL, true);
						goHome();
					}
				}, new DialogListener() {
					@Override
					public void onClick(Dialog dialog) {
						// TODO Auto-generated method stub
						PreferencesUtils.getInstance().setBooleanValue(
								PreferencesConfig.PIC_MODEL, false);
						goHome();
					}
				});
	}
}
