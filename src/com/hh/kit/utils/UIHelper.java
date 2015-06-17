/**  
 * Copyright © 2013 WHTY Tech Co. Ltd. All rights reserved.
 *
 * @Title: UIHelper.java
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月4日 上午10:30:00 
 */
package com.hh.kit.utils;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @ClassName: UIHelper
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月4日 上午10:30:00
 */
public class UIHelper {
	public UIHelper() {
	}

	public static final Pattern SIZED_VALUE = Pattern
			.compile("([0-9]*\\.?[0-9]+)\\W*(px|dp|dip|sp|sip|mm|pt|in)?");

	public static void styleText(TextView tv, String fontSize) {
		tv.setTextSize(getSizeUnits(fontSize), getSize(fontSize));
	}

	public static float getSize(String size) {
		float value = 15F;
		if (size != null) {
			Matcher m = SIZED_VALUE.matcher(size.trim());
			if (m.matches())
				value = Float.parseFloat(m.group(1));
		}
		return value;
	}

	public static int getSizeUnits(String size) {
		int units = 0;
		if (size != null) {
			Matcher m = SIZED_VALUE.matcher(size.trim());
			if (m.matches() && m.groupCount() == 2) {
				String unit = m.group(2);
				if ("px".equals(unit))
					units = 0;
				else if ("pt".equals(unit))
					units = 3;
				else if ("dp".equals(unit) || "dip".equals(unit))
					units = 1;
				else if ("sp".equals(unit) || "sip".equals(unit))
					units = 2;
				else if ("pt".equals(unit))
					units = 3;
				else if ("mm".equals(unit))
					units = 5;
				else if ("in".equals(unit))
					units = 4;
			}
		}
		return units;
	}

	public static Bitmap createBitmap(InputStream stream) {
		Rect pad = new Rect();
		android.graphics.BitmapFactory.Options opts = new android.graphics.BitmapFactory.Options();
		opts.inPurgeable = true;
		opts.inInputShareable = true;
		Bitmap b = null;
		try {
			b = BitmapFactory.decodeResourceStream(null, null, stream, pad,
					opts);
		} catch (OutOfMemoryError outofmemoryerror) {
		}
		return b;
	}

	/**
	 * 方法一: 比其他的两个方法多了一次计算,也就是多调用了一次onMeasure()方法,该方法虽然看上去简单,但是如果要目标控件计算耗时比较大的话(
	 * 如listView等),不建议使用. 方法二,它的回调方法会调用很多次,并且滑动TextView的时候任然会调用,所以不建议使用.
	 * 方法三,比较合适. 当然,实际应用的时候需要根据实际情况而定.
	 **/
	public void getWidthAndHeight(Context context) {
		final ImageView imageView = new ImageView(context);
		// ------------------------------------------------方法一
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		imageView.measure(w, h);
		int height = imageView.getMeasuredHeight();
		int width = imageView.getMeasuredWidth();

		// -----------------------------------------------方法二
		ViewTreeObserver vto = imageView.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				int height = imageView.getMeasuredHeight();
				int width = imageView.getMeasuredWidth();
				return true;
			}
		});
		// -----------------------------------------------方法三
		ViewTreeObserver vto2 = imageView.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				imageView.getViewTreeObserver().removeGlobalOnLayoutListener(
						this);
				int height = imageView.getHeight();
				int width = imageView.getWidth();
			}
		});
	}

}
