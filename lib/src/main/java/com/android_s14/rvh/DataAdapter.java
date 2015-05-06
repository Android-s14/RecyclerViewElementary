package com.android_s14.rvh;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

	private final List<DataModel> data;
	private final int textFieldsNumber;
	private final int imageFieldsNumber;
	private final Context context;
	private final OnClickListener listener;
	private final int rowLayout;
	private RecyclerViewBuilder.LayoutAttrs margins;
	private RecyclerViewBuilder.LayoutAttrs padding;
	private float cardCornerRadii;

	public DataAdapter(List<DataModel> data,
	                   Context context,
	                   OnClickListener listener,
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
		textFieldsNumber = data.get(0).getTextFieldsNumber();
		imageFieldsNumber = data.get(0).getImageFieldsNumber();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		final List<TextView> textViews = new ArrayList<>(textFieldsNumber);
		final List<ImageView> imageViews =
				imageFieldsNumber == 0 ? null : new ArrayList<ImageView>(imageFieldsNumber);

		public ViewHolder(CardView defaultRowLayout) {
			super(defaultRowLayout);
			LinearLayout internalLayout = (LinearLayout) defaultRowLayout.getChildAt(0);
			addImageViews(internalLayout);
			addTextViews(internalLayout);
		}

		private void addTextViews(LinearLayout internalLayout) {
			for (int i = 0; i < textFieldsNumber; i++) {
				TextView textView = new TextView(context);
				setUpDefaultView(textView);
				internalLayout.addView(textView);
				textViews.add(textView);
			}
		}

		private void addImageViews(LinearLayout internalLayout) {
			if (imageViews != null) {
				for (int i = 0; i < imageFieldsNumber; i++) {
					ImageView imageView = new ImageView(context);
					setUpDefaultView(imageView);
					internalLayout.addView(imageView);
					imageViews.add(imageView);
				}
			}
		}

		private void setUpDefaultView(View view) {
			LinearLayout.LayoutParams params =
					new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					                              ViewGroup.LayoutParams.WRAP_CONTENT);
			int margins = Utils.convertDpToPixel(5f, context);
			params.setMargins(margins, margins, margins, margins);
			view.setLayoutParams(params);
		}

		public ViewHolder(View customRowLayout) {
			super(customRowLayout);
			textViews.addAll(getAllViews(customRowLayout, TextView.class));
			if (imageViews != null) {
				imageViews.addAll(getAllViews(customRowLayout, ImageView.class));
			}
		}

		private <T extends View> List<T> getAllViews(View customRowLayout, Class<T> clz) {
			ArrayList<T> result = new ArrayList<>();
			ArrayList<View> children = Utils.getAllChildren(customRowLayout);
			for (View child : children) {
				if (clz.isInstance(child)) {
					@SuppressWarnings("unchecked")
					T textView = (T) child;
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
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		DataModel dataModel = data.get(position);
		List<TextView> textViews = viewHolder.textViews;
		if (listener != null) {
			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v, position);
				}
			});
		}
		for (int i = 0; i < textViews.size(); i++) {
			TextView textView = textViews.get(i);
			textView.setText(dataModel.getTextField(i));
		}

		if (viewHolder.imageViews != null) {
			List<ImageView> imageViews = viewHolder.imageViews;
			for (int i = 0; i < imageViews.size(); i++) {
				ImageView imageView = imageViews.get(i);
				imageView.setImageDrawable(dataModel.getImageField(i));
			}
		}
	}

	@Override
	public int getItemCount() {
		return data.size();
	}
}
