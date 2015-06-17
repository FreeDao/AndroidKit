package com.hh.kit.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hh.kit.R;

@SuppressLint("DrawAllocation")
public class DynamicPopView extends LinearLayout {

	private ViewGroup viewGroup;
	private Shader mShader;

	private int p = 0;
	private boolean show;
	private int delta = 0;

	/**
	 * @param context
	 * @param attrs
	 */
	public DynamicPopView(Context context) {
		this(context, null);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DynamicPopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		delta = (int) getResources().getDimension(R.dimen.popmargin);
		init(context);
	}

	private void init(Context context) {
		mLinePaint = new Paint();
		mLinePaint.setColor(Color.BLACK);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setStyle(Paint.Style.STROKE);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
//		mShader = new LinearGradient(0, 0, 100, 70, new int[] { Color.WHITE,
//				Color.WHITE, Color.WHITE }, null, Shader.TileMode.MIRROR);
//		mLinePaint.setShader(mShader);
//		mLinePaint.setStyle(Paint.Style.FILL);
		if (p != 0) {
			Path path = new Path();
			path.moveTo(0, 10);
			path.lineTo(p - 10, 10);
			path.lineTo(p, 0);
			path.lineTo(p + 10, 10);
			path.lineTo(width, 10);
			path.lineTo(width, height);
			path.lineTo(0, height);
			path.close();
			canvas.drawPath(path, mLinePaint);

		}
		super.onDraw(canvas);
	}

	public void setBreakPoint(int p) {
		this.p = p - delta;
		postInvalidate();
	}

	private Paint mLinePaint;

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	// MeasureSpec.AT_MOST);
	// super.onMeasure(widthMeasureSpec, expandSpec);
	// }

	public ViewGroup getViewGroup() {
		return viewGroup;
	}

	public void setViewGroup(ViewGroup viewGroup) {
		this.viewGroup = viewGroup;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (null != viewGroup) {
			viewGroup.requestDisallowInterceptTouchEvent(true);
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (null != viewGroup) {
			viewGroup.requestDisallowInterceptTouchEvent(true);
		}
		return super.onInterceptTouchEvent(arg0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (MotionEvent.ACTION_UP == arg0.getAction()) {
			if (null != viewGroup) {
				viewGroup.requestDisallowInterceptTouchEvent(false);
			}
		} else {
			if (null != viewGroup) {
				viewGroup.requestDisallowInterceptTouchEvent(true);
			}
		}
		return super.onTouchEvent(arg0);
	}

}
