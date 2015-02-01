package com.android_s14.rvh;

import android.graphics.drawable.Drawable;

public interface DataModel {

	/**
	 * @return a corresponding String field of the class
	 */
	String getTextField(int index);

	/**
	 * @return a number of text fields of this class showed in the RecyclerView
	 */
	int getTextFieldsNumber();

	/**
	 * @return a corresponding Drawable field of the class
	 */
	Drawable getImageField(int index);

	/**
	 * @return a number of image fields of this class showed in the RecyclerView (zero if none)
	 */
	int getImageFieldsNumber();

}
