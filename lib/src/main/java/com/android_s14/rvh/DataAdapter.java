package com.android_s14.rvh;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

	private final List<DataModel> data;
	private final int dataFieldsNumber;
	private final Context context;
	private final View.OnClickListener listener;
	private final int rowLayout;
	private RecyclerViewBuilder.LayoutAttrs margins;
	private RecyclerViewBuilder.LayoutAttrs padding;
	private float cardCornerRadii;

	public DataAdapter(List<DataModel> data,
	                   Context context,
	                   View.OnClickListener listener,
	                   int rowLayout,
	                   RecyclerViewBuilder.LayoutAttrs margins,
	                   RecyclerViewBuilder.LayoutAttrs padding, float cardCornerRadii) {
		this.data = data;
		this.context = context;
		this.listener = listener;
		this.rowLayout = rowLayout;
		this.margins = margins;
		this.padding = padding;
		this.cardCornerRadii = cardCornerRadii;
		dataFieldsNumber = data.get(0).getDataFieldsNumber();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		final List<TextView> textViews = new ArrayList<>(dataFieldsNumber);

		public ViewHolder(CardView defaultRowLayout) {
			super(defaultRowLayout);
			defaultRowLayout.setOnClickListener(listener);
			LinearLayout internalLayout = (LinearLayout) defaultRowLayout.getChildAt(0);
			for (int i = 0; i < dataFieldsNumber; i++) {
				TextView textView = new TextView(context);
				setUpDefaultTextView(textView);
				internalLayout.addView(textView);
				textViews.add(textView);
			}
		}

		private void setUpDefaultTextView(TextView textView) {
			LinearLayout.LayoutParams params =
					new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					                              ViewGroup.LayoutParams.WRAP_CONTENT);
			int margins = Utils.convertDpToPixel(5f, context);
			params.setMargins(margins, margins, margins, margins);
			textView.setLayoutParams(params);
		}

		public ViewHolder(View customRowLayout) {
			super(customRowLayout);
			customRowLayout.setOnClickListener(listener);
			textViews.addAll(getAllTextViews(customRowLayout));
		}

		private List<TextView> getAllTextViews(View customRowLayout) {
			ArrayList<TextView> result = new ArrayList<>();
			ArrayList<View> children = Utils.getAllChildren(customRowLayout);
			for (View child : children) {
				if (child instanceof TextView) {
					TextView textView = (TextView) child;
					result.add(textView);
				}
			}
			return result;
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		if (rowLayout == 0) {
			CardView externalView = new CardView(context);
			setUpDefaultRowView(externalView);
			externalView.addView(new LinearLayout(context));
			return new ViewHolder(externalView);
		} else {
			View customRowLayout = View.inflate(context, rowLayout, null);
			setUpCustomRowView(customRowLayout);
			return new ViewHolder(customRowLayout);
		}
	}

	private void setUpCustomRowView(View customRowLayout) {
		LinearLayout.LayoutParams layoutParams =
				new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				                              ViewGroup.LayoutParams.WRAP_CONTENT);
		if (margins == null) {
			int m = Utils.convertDpToPixel(5f, context);
			margins = new RecyclerViewBuilder.LayoutAttrs(m, m, m, m);
		}
		layoutParams.setMargins(margins.left, margins.top, margins.right, margins.bottom);
		customRowLayout.setLayoutParams(layoutParams);
	}

	private void setUpDefaultRowView(CardView externalView) {
		if (margins == null) {
			int m = Utils.convertDpToPixel(5f, context);
			margins = new RecyclerViewBuilder.LayoutAttrs(m, m, m, m);
		}
		if (padding == null) {
			int p = Utils.convertDpToPixel(10f, context);
			padding = new RecyclerViewBuilder.LayoutAttrs(p, p, p, p);
		}
		if (cardCornerRadii < 0) {
			cardCornerRadii = Utils.convertDpToPixel(10f, context);
		}
		externalView.setRadius(cardCornerRadii);
		externalView.setPadding(padding.left, padding.top, padding.right, padding.bottom);
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				                              ViewGroup.LayoutParams.WRAP_CONTENT);
		params.setMargins(margins.left, margins.top, margins.right, margins.bottom);
		externalView.setLayoutParams(params);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		DataModel dataModel = data.get(position);
		List<TextView> textViews = viewHolder.textViews;
		for (int i = 0; i < textViews.size(); i++) {
			TextView textView = textViews.get(i);
			textView.setText(dataModel.getTextField(i));
		}
	}

	@Override
	public int getItemCount() {
		return data.size();
	}
}
