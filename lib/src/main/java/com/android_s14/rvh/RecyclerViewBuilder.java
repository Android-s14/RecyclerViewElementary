package com.android_s14.rvh;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class RecyclerViewBuilder {

	private final Context context;
	private List<DataModel> data;
	private RecyclerView.LayoutManager layoutManager;
	private View.OnClickListener listener;
	private int rowLayout;
	private LayoutAttrs margins;
	private LayoutAttrs padding;
	private float cardCornerRadii = -1f;

	public RecyclerViewBuilder(Context context) {
		this.context = context;
	}

	static class LayoutAttrs {

		public final int left;
		public final int top;
		public final int right;
		public final int bottom;

		public LayoutAttrs(int left, int top, int right, int bottom) {
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
		}
	}

	public RecyclerView build() {

		RecyclerView recyclerView = new RecyclerView(context);
		setUpLayoutManager(recyclerView);
		setUpAdapter(recyclerView);

		return recyclerView;
	}

	private void setUpAdapter(RecyclerView recyclerView) {
		DataAdapter adapter = new DataAdapter(data, context, listener, rowLayout, margins,
		                                      padding, cardCornerRadii);
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
		this.rowLayout = rowLayout;
		return this;
	}

	public RecyclerViewBuilder setListener(View.OnClickListener listener) {
		this.listener = listener;
		return this;
	}

	public RecyclerViewBuilder setCardMargins(int left, int top, int right, int bottom) {
		this.margins = new LayoutAttrs(left, top, right, bottom);
		return this;
	}

	public RecyclerViewBuilder setCardPadding(int left, int top, int right, int bottom) {
		this.padding = new LayoutAttrs(left, top, right, bottom);
		return this;
	}

	public RecyclerViewBuilder setCardCornerRadii(float radius) {
		this.cardCornerRadii = radius;
		return this;
	}
}
