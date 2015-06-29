package com.hh.kit.home;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hh.kit.R;
import com.hh.kit.adapter.HomeAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private List<String> mDatas;
	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_recyclerview);
		initView();
		initRecyclerView();
	}

	private void initView() {
	}

	private void initRecyclerView() {

		mRecyclerView = (RecyclerView) findViewById(R.id.rv_recyclerview_data);

		// improve performance if you know that changes in content
		// do not change the size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		// specify an adapter (see also next example)
		mAdapter = new HomeAdapter(this, mDatas);
		mRecyclerView.setAdapter(mAdapter);

		// 设置Item增加、移除动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		// 添加分割线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
				DividerItemDecoration.VERTICAL_LIST) {
		});
	}

	protected void initData() {
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++) {
			mDatas.add("" + (char) i);
		}
	}

}
