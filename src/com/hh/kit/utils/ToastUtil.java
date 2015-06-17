package com.hh.kit.utils;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.kit.R;

/**
 * @ClassName: ToastUtil
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月4日 下午2:52:13
 */
public class ToastUtil {
	static TextView tv;
	static WindowManager mWindowManager;

	/**
	 * @Title: showMessage
	 * @Description: TODO
	 * @param act
	 * @param msg
	 * @return: void
	 */
	public static void showMessage(final Context act, final String msg) {
		try {
			showMessage(act, msg, Toast.LENGTH_SHORT);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void showMessage(final Context act, final int msg) {
		try {
			showMessage(act, act.getString(msg), Toast.LENGTH_SHORT);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void showMessage(Context context, String msg, int Length) {
		mWindowManager = (WindowManager) context.getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		if (tv == null) {
			tv = new TextView(context);
			UIHelper.styleText(tv, "16sp");
			tv.setTextColor(context.getResources().getColor(R.color.white));
			tv.setBackgroundResource(R.drawable.dialogbox_down);
			int padding = DisplayUtils.dipToPixel(10);
			tv.setPadding(padding, padding, padding, padding);
		}
		tv.setText(msg);
		if (tv.getParent() == null) {
			WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			params.gravity = Gravity.BOTTOM;
			params.alpha = 0.85f;
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
			params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
					| WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
			params.format = PixelFormat.TRANSLUCENT;
			params.verticalMargin = 0.2f;
			params.windowAnimations = 0;
			mWindowManager.addView(tv, params);
			handler.sendEmptyMessageDelayed(101, 2000);
		} else {
			handler.removeMessages(101);
			handler.sendEmptyMessageDelayed(101, 2000);
		}
	}

	static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tv.getParent() != null)
				mWindowManager.removeView(tv);
		}
	};

	public static void cancelCurrentToast() {
		if (tv != null && tv.getParent() != null) {
			mWindowManager.removeView(tv);
		}
	}
}
