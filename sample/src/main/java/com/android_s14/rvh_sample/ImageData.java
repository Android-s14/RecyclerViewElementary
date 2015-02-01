package com.android_s14.rvh_sample;

import android.graphics.drawable.Drawable;

import com.android_s14.rvh.DataModel;

public class ImageData implements DataModel {

	private final String firstName;
	private final String lastName;
	private final Drawable photo;

	public ImageData(String firstName, String lastName, Drawable photo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
	}

	@Override
	public String getTextField(int index) {
		switch (index) {
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
		return photo;
	}

	@Override
	public int getImageFieldsNumber() {
		return 1;
	}
}
