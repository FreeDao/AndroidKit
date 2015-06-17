package com.hh.kit.home;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hh.kit.CommonActivity;
import com.hh.kit.R;
import com.hh.kit.utils.Log;
import com.hh.kit.views.CircleImageView;
import com.hh.kit.views.DynamicPopShowLoader;
import com.hh.kit.views.RefreshableView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class HomeMainActivity extends CommonActivity {
	private RefreshableView refreshview;
	private DynamicPopShowLoader mSort;
	private Fragment mFragment;
	Button tv;
	private CircleImageView cir;
	private DisplayImageOptions options;
	private EditText et_ssid, et_password, et_mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_main);
		findViewById(R.id.volley).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoTestVolley();
			}
		});

		et_ssid = (EditText) findViewById(R.id.et_ssid);
		et_password = (EditText) findViewById(R.id.et_password);
		et_mode = (EditText) findViewById(R.id.et_mode);
		et_ssid.setText("wifi-1");
		et_password.setText("hh123654");
		et_mode.setText("3");
		findViewById(R.id.wifi).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WifiAdmin wifiAdmin = new WifiAdmin(HomeMainActivity.this);
				wifiAdmin.openWifi();
				wifiAdmin.addNetwork(wifiAdmin.CreateWifiInfo(et_ssid.getText()
						.toString(), et_password.getText().toString(), Integer
						.parseInt(et_mode.getText().toString())));
				// int netId = mWifiManager.addNetwork(wifiConfig);
				// mWifiManager.enableNetwork(netId, true);
			}
		});
		findViewById(R.id.wifi1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WifiAdmin1 wifiAdmin = new WifiAdmin1(HomeMainActivity.this);
				wifiAdmin.openWifi();
				wifiAdmin.addNetwork(wifiAdmin.CreateWifiInfo(et_ssid.getText()
						.toString(), et_password.getText().toString(), Integer
						.parseInt(et_mode.getText().toString())));
				// int netId = mWifiManager.addNetwork(wifiConfig);
				// mWifiManager.enableNetwork(netId, true);
			}
		});
		// setContentView(R.layout.fragment_main);
		// int f = getIntent().getIntExtra("int", 0);
		//
		// System.out.println("" + f);

		// options = new DisplayImageOptions.Builder().cacheInMemory()
		// .cacheOnDisc().showImageForEmptyUri(R.drawable.hugh)
		// .showImageOnFail(R.drawable.hugh)
		// .bitmapConfig(Bitmap.Config.RGB_565)
		// .showStubImage(R.drawable.hugh).build();
		// cir = (CircleImageView) findViewById(R.id.webCirImage);
		// cir.setURLDisplayAsync(
		// "http://image.tianjimedia.com/uploadImages/2013/156/R97BYJ34UOF9.jpg",
		// options);

		// tv = (Button) findViewById(R.id.btn);
		// initPopupWindow();
		// tv.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// if (mSort.isShow()) {
		// mSort.hidePop();
		// } else {
		// mSort.showPop(v, false);
		// }
		// }
		// });
		// if (savedInstanceState == null) {
		// mFragment = getSupportFragmentManager().findFragmentById(
		// R.id.container);
		// getSupportFragmentManager().beginTransaction().show(mFragment)
		// .commit();
		// }

	}

	private void gotoTestVolley() {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		StringRequest stringRequest = new StringRequest("http://www.baidu.com",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("TAG", response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		mQueue.add(stringRequest);
	}

	// Android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent(String param) {

		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		String url = "http://218.206.30.144:8080/upload/headImg/1397445456478.doc";
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	private void initPopupWindow() {
		mSort = new DynamicPopShowLoader();
		mSort.initSortPop(HomeMainActivity.this, "12121212121");
	}

	public static class MainFragment extends Fragment {

		public MainFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	/**
	 * @Title: startLoad
	 * @Description: TODO
	 */
	protected void startLoad() {
		// TODO Auto-generated method stub
		// new CountDownTimer(5000, 1000) {
		// public void onTick(long millisUntilFinished) {
		// }
		//
		// public void onFinish() {
		// refreshview.finishRefresh();
		// }
		// }.start();
		// new Runnable() {
		// public void run() {
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// refreshview.finishRefresh();
		// }
		// }.run();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_main, menu);
		return true;
	}

}
