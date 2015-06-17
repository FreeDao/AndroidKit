package com.hh.kit.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @ClassName: MarqueeTextView
 * @Description: 自动滚动跑马灯
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午5:00:12
 */
public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context con) {
		super(con);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
	}

}
