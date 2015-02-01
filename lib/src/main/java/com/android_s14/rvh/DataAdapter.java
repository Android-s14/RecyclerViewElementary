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
	private Context context;
	private View.OnClickListener listener;

	public DataAdapter(List<DataModel> data, Context context, View.OnClickListener listener) {
		this.data = data;
		this.context = context;
		this.listener = listener;
		dataFieldsNumber = data.get(0).getDataFieldsNumber();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		protected List<TextView> textViews = new ArrayList<>(dataFieldsNumber);

		public ViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(listener);
			LinearLayout internalLayout = (LinearLayout) ((CardView) itemView).getChildAt(0);
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
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		CardView externalView = new CardView(context);
		setUpDefaultExternalView(externalView);
		externalView.addView(new LinearLayout(context));
		return new ViewHolder(externalView);
	}

	private void setUpDefaultExternalView(CardView externalView) {
		int margins = Utils.convertDpToPixel(5f, context);
		int padding = Utils.convertDpToPixel(10f, context);
		externalView.setRadius(Utils.convertDpToPixel(10f, context));
		externalView.setPadding(padding, padding, padding, padding);
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				                              ViewGroup.LayoutParams.WRAP_CONTENT);
		params.setMargins(margins, margins, margins, margins);
		externalView.setLayoutParams(params);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		DataModel dataModel = data.get(position);
		List<TextView> textViews = viewHolder.textViews;
		for (int i = 0, textViewsSize = textViews.size(); i < textViewsSize; i++) {
			TextView textView = textViews.get(i);
			textView.setText(dataModel.getTextField(i));
		}
	}

	@Override
	public int getItemCount() {
		return data.size();
	}
}
