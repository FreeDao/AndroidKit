package com.hh.kit.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SwipeableViewPager extends ViewPager {

    private boolean isEnable = true;

    /**
     * @param context
     * @param attrs
     */
    public SwipeableViewPager(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public SwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isEnable) {
            return super.onInterceptTouchEvent(arg0);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnable) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void setViewPagerEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }
}
