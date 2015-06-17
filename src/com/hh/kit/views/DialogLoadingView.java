package com.hh.kit.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hh.kit.R;

public class DialogLoadingView extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	private SurfaceHolder mSurfaceHolder;
	private int speed = 180;
	private boolean mLoop = false;
	private int bitmapFront_Width = 0, bitmapFront_Height = 0;
	private Bitmap bitmapFornt = null, bitmapBack = null;
	private int Mask_Step = 15;
	private int Mask_Legth = 0;

	public DialogLoadingView(Context context) {
		super(context, null);
	}

	public DialogLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		mLoop = true;
		this.setZOrderOnTop(true);
		mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		startAnim();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mLoop = false;
		if (null != bitmapFornt) {
			bitmapFornt.recycle();
		}
		if (null != bitmapBack) {
			bitmapBack.recycle();
		}

	}

	private void startAnim() {
		Resources res = this.getResources();
		bitmapBack = BitmapFactory.decodeResource(res,
				R.drawable.commondialog_loading_01);
		bitmapFornt = BitmapFactory.decodeResource(res,
				R.drawable.commondialog_loading_12);
		bitmapFront_Width = bitmapFornt.getWidth();
		bitmapFront_Height = bitmapFornt.getHeight();
		Mask_Legth = bitmapFront_Height;
		mLoop = true;
		new Thread(this).start();
	}

	private void drawImg() {

		if (null == mSurfaceHolder) {
			return;
		}
		Canvas canvas = mSurfaceHolder.lockCanvas();
		if (null == canvas) {
			return;
		}

		try {
			if (Mask_Legth <= 0) {
				Mask_Legth = bitmapFront_Height;
			}
			Mask_Legth -= Mask_Step;

			Paint paint = new Paint();
			paint.setDither(true);
			paint.setFilterBitmap(false);
			paint.setColor(Color.WHITE);

			canvas.drawBitmap(bitmapBack, 0, 0, paint);

			int rc = canvas.saveLayer(0, 0, bitmapFront_Width,
					bitmapFront_Height, null, Canvas.ALL_SAVE_FLAG);

			canvas.drawRect(0, 0, bitmapFront_Width, Mask_Legth, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
			canvas.drawBitmap(bitmapFornt, 0, 0, paint);
			paint.setXfermode(null);
			canvas.restoreToCount(rc);

		} catch (Exception ex) {
			return;
		} finally {
			mSurfaceHolder.unlockCanvasAndPost(canvas);
		}

	}

	@Override
	public void run() {
		while (mLoop) {
			synchronized (mSurfaceHolder) {
				drawImg();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
