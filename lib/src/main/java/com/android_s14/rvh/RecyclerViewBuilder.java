package com.android_s14.rvh;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class RecyclerViewBuilder {

	private Context context;
	private List<DataModel> data;
	private RecyclerView.LayoutManager layoutManager;

	public RecyclerViewBuilder(Context context) {
		this.context = context;
	}

	public RecyclerView build() {

		RecyclerView recyclerView = new RecyclerView(context);
		setUpLayoutManager(recyclerView);
		setUpAdapter(recyclerView);

		return recyclerView;
	}

	private void setUpAdapter(RecyclerView recyclerView) {
		DataAdapter adapter = new DataAdapter(data, context);
		recyclerView.setAdapter(adapter);
	}

	private void setUpLayoutManager(RecyclerView recyclerView) {
		if (layoutManager == null) {
			layoutManager = new LinearLayoutManager(context);
			((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
		}
		recyclerView.setLayoutManager(layoutManager);
	}

	public RecyclerViewBuilder setData(List<DataModel> data) {
		this.data = data;
		return this;
	}

	public RecyclerViewBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
		this.layoutManager = layoutManager;
		return this;
	}

	public RecyclerViewBuilder setRowLayout(@LayoutRes int rowLayout) {
		//todo implement
		return this;
	}
}
