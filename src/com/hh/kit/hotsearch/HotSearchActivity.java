package com.hh.kit.hotsearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.hh.kit.R;
import com.hh.kit.utils.ToastUtil;

public class HotSearchActivity extends Activity implements OnTouchListener,
		OnClickListener, OnGestureListener {

	AutoCompleteTextView keyword_app_search;
	View search_closebtn, btn_app_search;
	KeyWordsFlow keywordsFlow;
	List<String> items = new ArrayList<String>();
	GestureDetector mGestureDetector;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	public String[] keywords = null;
	int keyCount = 10;

	HotSearchDataBase base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_main);
		mGestureDetector = new GestureDetector(this, this);
		initView();
		base = HotSearchDataBase.getInstance(this);
		keywords = setToArray(base.getRecommendState());
		feedKeywordsFlow(keywordsFlow, keywords);
		keywordsFlow.go2Show(KeyWordsFlow.ANIMATION_IN);
	}

	private void initView() {
		keyword_app_search = (AutoCompleteTextView) findViewById(R.id.keyword_app_search);
		search_closebtn = findViewById(R.id.btn_search_close);
		btn_app_search = findViewById(R.id.btn_app_search);
		keywordsFlow = (KeyWordsFlow) findViewById(R.id.keywordsFlow);
		search_closebtn.setOnClickListener(this);
		btn_app_search.setOnClickListener(this);
		// 自动完成设置
		keyword_app_search.setThreshold(1);// 设置输入一位就提示
		keyword_app_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				// 数据库里面和这个有关的去填充items
				items = setToList(base.getRecommendState(keyword_app_search
						.getText().toString().trim()));
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						getApplicationContext(),
						android.R.layout.simple_dropdown_item_1line, items);
				keyword_app_search.setAdapter(adapter);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		keywordsFlow.setDuration(800l);
		keywordsFlow.setOnItemClickListener(this);
		findViewById(R.id.layout_search_result).setOnTouchListener(this);
		findViewById(R.id.layout_search_result).setLongClickable(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_search_close:
			keyword_app_search.setText("");
			break;
		case R.id.btn_app_search:
			HotSearchBean bean = new HotSearchBean();
			bean.setName(keyword_app_search.getText().toString());
			long time = System.currentTimeMillis();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
			bean.setTime(sf.format(new Date(time)));
			bean.setUserid("");
			base.addHistorySearch(bean);
			// ToastUtil.showMessage(SearchWorldActivity.this, "你有一条新的记录");
			keyword_app_search.setText("");
			break;

		default:
			if (v instanceof TextView) {
				// 获取关键词
				String keyword = ((TextView) v).getText().toString();
				if (!TextUtils.isEmpty(keyword)) {
					if (keyword.length() > 10) {
						ToastUtil.showMessage(HotSearchActivity.this, "搜索的字过长");
					} else {
						keyword_app_search.setText(keyword);
						keyword_app_search.setSelection(keyword.length());
						// do something
					}
				}

			}
			break;
		}

	}

	void keywordsFlowClick() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.
	 * MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onShowPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.
	 * view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onScroll(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onLongPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// 向左滑动
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			keywordsFlow.rubKeywords();
			feedKeywordsFlow(keywordsFlow, keywords);
			keywordsFlow.go2Show(KeyWordsFlow.ANIMATION_IN);
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// 向右滑动
			// 清除关键词
			keywordsFlow.rubKeywords();
			// 生成随机关键词
			feedKeywordsFlow(keywordsFlow, keywords);
			// 关键词生成动画效果
			keywordsFlow.go2Show(KeyWordsFlow.ANIMATION_OUT);
		} else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
			keywordsFlow.rubKeywords();
			feedKeywordsFlow(keywordsFlow, keywords);
			keywordsFlow.go2Show(KeyWordsFlow.ANIMATION_IN);
		} else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE) {
			// 向右滑动
			// 清除关键词
			keywordsFlow.rubKeywords();
			// 生成随机关键词
			feedKeywordsFlow(keywordsFlow, keywords);
			// 关键词生成动画效果
			keywordsFlow.go2Show(KeyWordsFlow.ANIMATION_OUT);
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);
	}

	/**
	 * 随机关键词
	 * 
	 * @param keywordsFlow
	 * @param arr
	 */
	private void feedKeywordsFlow(KeyWordsFlow keywordsFlow, String[] arr) {
		Random random = new Random();
		if (arr != null && arr.length > 0) {

			for (int i = 0; i < keyCount; i++) {
				int ran = random.nextInt(arr.length);
				String tmp = arr[ran];
				keywordsFlow.feedKeyword(tmp);
			}
		}
	}

	/**
	 * 数据转换 *
	 * 
	 * @param list
	 * @return
	 */
	private String[] setToArray(List<HotSearchBean> list) {
		String[] temp = null;
		int count = list.size();
		temp = new String[count];
		for (int i = 0; i < count; i++) {
			temp[i] = list.get(i).getName();
		}
		// 设置页面显示的关键词数量，如果关键词超过10，则只能显示10个
		if (count < keyCount) {
			keyCount = count;
		}

		return temp;
	}

	/**
	 * 数据转换
	 * 
	 * @param list
	 * @return
	 */
	private List<String> setToList(List<HotSearchBean> list) {
		List<String> temp = new ArrayList<String>();
		int count = list.size();
		for (int i = 0; i < count; i++) {
			temp.add(list.get(i).getName());
		}
		// 设置页面显示的关键词数量，如果关键词超过10，则只能显示10个
		if (count < keyCount) {
			keyCount = count;
		}

		return temp;
	}
}
