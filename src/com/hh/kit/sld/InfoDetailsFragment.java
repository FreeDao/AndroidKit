package com.hh.kit.sld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hh.kit.R;

/**
 * Author : yanbo Date : 2015-06-01 Time : 15:09 Description :
 */
public class InfoDetailsFragment extends Fragment {
	private RecyclerView mRecyclerView;
	private onDataLoadedLinstener listener;

	public interface onDataLoadedLinstener {
		public void onDataLoaded(int num);
	}

	public onDataLoadedLinstener getListener() {
		return listener;
	}

	public void setOnDataLoadedLinstener(onDataLoadedLinstener listener) {
		this.listener = listener;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRecyclerView = (RecyclerView) inflater.inflate(
				R.layout.info_details_fragment, container, false);
		return mRecyclerView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView
				.getContext()));
		mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity()));
		if (listener != null) {
			listener.onDataLoaded(12);
		}
	}
}
