package com.hh.kit.home;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.hh.kit.R;
import com.hh.kit.views.CommonPageAdapter;

public class ScrollViewPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private int mImages[] = new int[] { R.drawable.sh_welcome1,
			R.drawable.sh_welcome2, R.drawable.sh_welcome3 };
	private String mTitles[] = new String[] { "", "", "" };
	private ArrayList<View> mList;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_scrollviewpager);
		initView();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.scroll_view_pager);
		// 初始化TabLayout的title数据集
		mList = new ArrayList<View>();
		for (int i = 0; i < mImages.length; i++) {
			View v = new ImageView(this);
			((ImageView) v).setScaleType(ScaleType.FIT_XY);
			((ImageView) v).setImageResource(mImages[i]);
			mList.add(v);
		}
		mViewPager.setAdapter(new CommonPageAdapter(this, mList, mTitles));
	}

}
