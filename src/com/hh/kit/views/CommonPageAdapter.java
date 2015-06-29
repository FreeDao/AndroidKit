package com.hh.kit.views;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CommonPageAdapter extends PagerAdapter {

	private ArrayList<View> mView;
	private String[] titles;

	public CommonPageAdapter(Context context, ArrayList<View> view,
			String[] titles) {
		this.mView = view;
		this.titles = titles;
	}

	@Override
	public void destroyItem(View container, int position, Object obj) {
		((ViewPager) container).removeView((View) obj);
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public int getCount() {
		return mView.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(mView.get(position));
		return mView.get(position);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

}
