package com.android_s14.rvh_sample;

import android.graphics.drawable.Drawable;

import com.android_s14.rvh.DataModel;

class TextData implements DataModel {

	private final String firstName;
	private final String lastName;

	public TextData(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String getTextField(int i) {
		switch (i) {
			case 0:
				return firstName;
			case 1:
				return lastName;
			default:
				return firstName;
		}
	}

	@Override
	public int getTextFieldsNumber() {
		return 2;
	}

	@Override
	public Drawable getImageField(int index) {
		return null;
	}

	@Override
	public int getImageFieldsNumber() {
		return 0;
	}
}
