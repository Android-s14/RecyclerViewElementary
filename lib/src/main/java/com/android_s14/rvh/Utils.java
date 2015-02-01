package com.android_s14.rvh;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

	private Utils() {
	}

	public static int convertDpToPixel(float dp, Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) (dp * (metrics.densityDpi / 160f));
	}
}