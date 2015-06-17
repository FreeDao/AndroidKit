package com.hh.kit.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hh.kit.R;

/**
 * grid的pop
 * 
 * @author wangwu
 * 
 */
/**
 * 
 * 使用方式 popWindowShow.initSortPop(this, list); popWindowShow.setListeners(new
 * PopListeners() { 点击 if (popWindowShow.isShow()) { popWindowShow.hidePop(); }
 * else { popWindowShow.showPop(v, false); }
 * 
 * 销毁
 * 
 * @Override protected void onDestroy() { // TODO Auto-generated method stub
 *           super.onDestroy(); if (popWindowShow.isShow()) {
 *           popWindowShow.hidePop(); }
 */
public class DynamicPopShowLoader {
	private DynamicPopShowLoader sInstance;
	private PopupWindow sortPop;
	private DynamicPopView dPopView;
	private final static int screenWidth = Resources.getSystem()
			.getDisplayMetrics().widthPixels;
	private final static int screenHeight = Resources.getSystem()
			.getDisplayMetrics().heightPixels;

	public void initSortPop(Context context, String text) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View popView = inflater.inflate(R.layout.dynamic_pop_view, null);
		sortPop = new PopupWindow(popView, screenWidth / 2,
				ViewGroup.LayoutParams.WRAP_CONTENT, false);

		dPopView = (DynamicPopView) popView.findViewById(R.id.dPopView);
		sortPop.setBackgroundDrawable(new BitmapDrawable());

		TextView titleView = (TextView) popView.findViewById(R.id.title);
		Button btn = (Button) popView.findViewById(R.id.btn);
		titleView.setText(text);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (sortPop != null && sortPop.isShowing())
					sortPop.dismiss();
			}
		});

	}

	public DynamicPopShowLoader() {
		super();
	}

	public void showPop(View v, boolean isTop) {
		if (sortPop == null) {
			return;
		}
		int[] xy = new int[2];
		v.getLocationInWindow(xy);
		dPopView.setBreakPoint(xy[0] + v.getWidth() / 2);

		if (isTop) {
			sortPop.showAtLocation(v, Gravity.TOP, 0, 0);
		} else {
			sortPop.showAsDropDown(v, 0, 0);
		}
	}

	public boolean isShow() {
		if (sortPop != null && sortPop.isShowing()) {
			return true;
		}
		return false;
	}

	public void hidePop() {
		if (sortPop != null && sortPop.isShowing())
			sortPop.dismiss();
	}
}
