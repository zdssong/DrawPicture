package com.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Tools {

	/**
	 * 返回当前Activity所在的窗口的宽度
	 * */
	public final static int getWindowWidth(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	/**
	 * 返回当前Activity所在的窗口的高度
	 * */
	public final static int getWindowHeigh(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}
}
