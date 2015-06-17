package com.hh.kit.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hh.kit.R;

public class DialogUtils {

	public interface DialogListener {
		public void onClick(final Dialog dialog);
	}

	public static void closeDialog(Dialog dialog) {
		if (null != dialog) {
			dialog.dismiss();
		}
	}

	public static Dialog showDialogWithClose(Context context, Integer layoutId,
			String DialogTitle, final DialogListener leftButtonClickListener) {
		return showDialogWithClose(
				context,
				layoutId,
				DialogTitle,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle),
				leftButtonClickListener);
	}

	public static Dialog showDialogWithClose(Context context, Integer layoutId,
			String dialogTitle, String leftButtonTitle,
			String rightButtonTitle,
			final DialogListener leftButtonClickListener) {
		final Dialog dialog = new Dialog(context, R.style.CommonDialog);
		dialog.setContentView(R.layout.commondialog_withclose);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams params = dialogWindow.getAttributes();
		// WindowManager m = ((Activity) context).getWindowManager();
		// Display d = m.getDefaultDisplay();
		// params.width = (int) (d.getWidth() * 0.95);
		params.gravity = Gravity.CENTER;
		dialogWindow.setAttributes(params);
		dialog.show();
		TextView commondialog_title = (TextView) dialog
				.findViewById(R.id.commondialog_title);
		if (!TextUtils.isEmpty(dialogTitle)) {
			commondialog_title.setVisibility(View.VISIBLE);
			commondialog_title.setText(dialogTitle);
		} else {
			commondialog_title.setVisibility(View.GONE);
		}

		Button leftButton = (Button) dialog
				.findViewById(R.id.commondialog_leftbtn);
		if (!TextUtils.isEmpty(leftButtonTitle)) {
			leftButton.setVisibility(View.VISIBLE);
			leftButton.setText(leftButtonTitle);
		} else {
			leftButton.setVisibility(View.GONE);
		}
		if (null != leftButtonClickListener) {
			leftButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					leftButtonClickListener.onClick(dialog);
				}
			});
		}

		Button rightButton = (Button) dialog
				.findViewById(R.id.commondialog_rightbtn);
		if (!TextUtils.isEmpty(rightButtonTitle)) {
			rightButton.setVisibility(View.VISIBLE);
			rightButton.setText(rightButtonTitle);
		} else {
			rightButton.setVisibility(View.GONE);
		}
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != dialog) {
					dialog.dismiss();
				}
			}
		});

		if (null != layoutId) {
			LinearLayout commondialog_contentlayout = (LinearLayout) dialog
					.findViewById(R.id.commondialog_contentlayout);
			LayoutInflater mInflater = LayoutInflater.from(context);
			mInflater.inflate(layoutId, commondialog_contentlayout);
			commondialog_contentlayout.setVisibility(View.VISIBLE);
		}
		return dialog;
	}

	public static Dialog showDialogThreeButton(Context context, String content,
			String dialogTitle, String leftButtonTitle,
			String rightButtonTitle, String centerButtonTitle,
			final DialogListener leftButtonClickListener,
			final DialogListener centerButtonClickListener,
			final DialogListener rightButtonClickListener) {
		final Dialog dialog = new Dialog(context, R.style.CommonDialog);
		dialog.setContentView(R.layout.commondialog_three_button);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams params = dialogWindow.getAttributes();
		// WindowManager m = ((Activity) context).getWindowManager();
		// Display d = m.getDefaultDisplay();
		// params.width = (int) (d.getWidth() * 0.95);
		params.gravity = Gravity.CENTER;
		dialogWindow.setAttributes(params);
		dialog.show();
		TextView commondialog_title = (TextView) dialog
				.findViewById(R.id.commondialog_title);
		if (!TextUtils.isEmpty(dialogTitle)) {
			commondialog_title.setVisibility(View.VISIBLE);
			commondialog_title.setText(dialogTitle);
		} else {
			commondialog_title.setVisibility(View.GONE);
		}

		Button leftButton = (Button) dialog
				.findViewById(R.id.commondialog_leftbtn);
		if (!TextUtils.isEmpty(leftButtonTitle)) {
			leftButton.setVisibility(View.VISIBLE);
			leftButton.setText(leftButtonTitle);
		} else {
			leftButton.setVisibility(View.GONE);
		}
		if (null != leftButtonClickListener) {
			leftButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					leftButtonClickListener.onClick(dialog);
				}
			});
		}

		Button centerButton = (Button) dialog
				.findViewById(R.id.commondialog_centerbtn);
		if (!TextUtils.isEmpty(centerButtonTitle)) {
			centerButton.setVisibility(View.VISIBLE);
			centerButton.setText(centerButtonTitle);
		} else {
			centerButton.setVisibility(View.GONE);
		}
		if (null != leftButtonClickListener) {
			centerButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					centerButtonClickListener.onClick(dialog);
				}
			});
		}

		Button rightButton = (Button) dialog
				.findViewById(R.id.commondialog_rightbtn);
		if (!TextUtils.isEmpty(rightButtonTitle)) {
			rightButton.setVisibility(View.VISIBLE);
			rightButton.setText(rightButtonTitle);
		} else {
			rightButton.setVisibility(View.GONE);
		}
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rightButtonClickListener.onClick(dialog);
			}
		});

		if (!TextUtils.isEmpty(content)) {
			TextView contentTv = (TextView) dialog.findViewById(R.id.content);
			contentTv.setText(content);

		}
		return dialog;
	}

	public static Dialog showLoadingDialog(Context context) {
		return showLoadingDialog(context, "");
	}

	public static Dialog showLoadingDialog(Context context, String loadingText) {
		final Dialog dialog = new Dialog(context, R.style.CommonDialog);
		dialog.setContentView(R.layout.commondialog_loading);
		dialog.show();
		TextView commondialog_loading_msg = (TextView) dialog
				.findViewById(R.id.commondialog_loading_msg);
		if (!TextUtils.isEmpty(loadingText)) {
			commondialog_loading_msg.setVisibility(View.VISIBLE);
			commondialog_loading_msg.setText(loadingText);
		} else {
			commondialog_loading_msg.setVisibility(View.GONE);
			commondialog_loading_msg.setText("");
		}
		return dialog;
	}

	public static Dialog showOneButtonDialog(Context context, String Msg) {
		return showTwoButtonDialog(
				context,
				context.getResources().getString(
						R.string.commondialog_defaulttoptitle),
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle), "", null, null);
	}

	public static Dialog showOneButtonDialog(Context context,
			String dialogTitle, String Msg) {
		return showTwoButtonDialog(context, dialogTitle, Msg, context
				.getResources().getString(R.string.commondialog_comfirmtitle),
				"", null, null);
	}

	public static Dialog showOneButtonDialog(Context context,
			String dialogTitle, String Msg, String ButtonTxext) {
		return showTwoButtonDialog(context, dialogTitle, Msg, ButtonTxext, "",
				null, null);
	}

	public static Dialog showOneButtonDialog(Context context, String Msg,
			final DialogListener buttonClickListener) {
		return showTwoButtonDialog(
				context,
				context.getResources().getString(
						R.string.commondialog_defaulttoptitle),
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle), "",
				buttonClickListener, null);
	}

	public static Dialog showOneButtonDialog(Context context,
			String dialogTitle, String Msg,
			final DialogListener buttonClickListener) {
		return showTwoButtonDialog(context, dialogTitle, Msg, context
				.getResources().getString(R.string.commondialog_comfirmtitle),
				"", buttonClickListener, null);
	}

	public static Dialog showOneButtonDialog(Context context,
			String dialogTitle, String Msg, String ButtonTxext,
			final DialogListener buttonClickListener) {
		return showTwoButtonDialog(context, dialogTitle, Msg, ButtonTxext, "",
				buttonClickListener, null);
	}

	public static Dialog showConfirmDialog(Context context, String Msg) {
		return showTwoButtonDialog(
				context,
				context.getResources().getString(
						R.string.commondialog_defaulttoptitle),
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle), null, null);
	}

	public static Dialog showConfirmDialog(Context context, String Msg,
			final DialogListener leftButtonClickListener) {
		return showTwoButtonDialog(
				context,
				context.getResources().getString(
						R.string.commondialog_defaulttoptitle),
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle),
				leftButtonClickListener, null);
	}

	public static Dialog showConfirmDialog(Context context, String Msg,
			final DialogListener leftButtonClickListener,
			final DialogListener rightButtonClickListener) {
		return showTwoButtonDialog(
				context,
				context.getResources().getString(
						R.string.commondialog_defaulttoptitle),
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle),
				leftButtonClickListener, rightButtonClickListener);
	}

	public static Dialog showConfirmDialog(Context context, String dialogTitle,
			String Msg, final DialogListener leftButtonClickListener) {
		return showTwoButtonDialog(
				context,
				dialogTitle,
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle),
				leftButtonClickListener, null);
	}

	public static Dialog showConfirmDialog(Context context, String dialogTitle,
			String Msg, final DialogListener leftButtonClickListener,
			final DialogListener rightButtonClickListener) {
		return showTwoButtonDialog(
				context,
				dialogTitle,
				Msg,
				context.getResources().getString(
						R.string.commondialog_comfirmtitle),
				context.getResources().getString(
						R.string.commondialog_cancletitle),
				leftButtonClickListener, rightButtonClickListener);
	}

	public static Dialog showTwoButtonDialog(Context context,
			String dialogTitle, String Msg, String leftButtonTitle,
			String rightButtonTitle,
			final DialogListener leftButtonClickListener,
			final DialogListener rightButtonClickListener) {
		final Dialog dialog = new Dialog(context, R.style.CommonDialog);
		dialog.setContentView(R.layout.commondialog_withclose);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams params = dialogWindow.getAttributes();
		// WindowManager m = ((Activity) context).getWindowManager();
		// Display d = m.getDefaultDisplay();
		dialogWindow.setGravity(Gravity.CENTER);
		dialogWindow.setAttributes(params);
		dialog.show();
		TextView commondialog_title = (TextView) dialog
				.findViewById(R.id.commondialog_title);
		if (!TextUtils.isEmpty(dialogTitle)) {
			commondialog_title.setVisibility(View.VISIBLE);
			commondialog_title.setText(dialogTitle);
		} else {
			commondialog_title.setVisibility(View.GONE);
		}

		Button leftButton = (Button) dialog
				.findViewById(R.id.commondialog_leftbtn);
		if (!TextUtils.isEmpty(leftButtonTitle)) {
			leftButton.setVisibility(View.VISIBLE);
			leftButton.setText(leftButtonTitle);
		} else {
			leftButton.setVisibility(View.GONE);
		}

		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != leftButtonClickListener) {
					leftButtonClickListener.onClick(dialog);
				} else {
					if (null != dialog) {
						dialog.dismiss();
					}
				}
			}
		});

		Button rightButton = (Button) dialog
				.findViewById(R.id.commondialog_rightbtn);
		if (!TextUtils.isEmpty(rightButtonTitle)) {
			rightButton.setVisibility(View.VISIBLE);
			rightButton.setText(rightButtonTitle);
		} else {
			rightButton.setVisibility(View.GONE);
		}

		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null == rightButtonClickListener) {
					if (null != dialog) {
						dialog.dismiss();
					}
				} else {
					rightButtonClickListener.onClick(dialog);
				}
			}
		});

		if (!TextUtils.isEmpty(Msg)) {
			LinearLayout commondialog_contentlayout = (LinearLayout) dialog
					.findViewById(R.id.commondialog_contentlayout);
			LayoutInflater mInflater = LayoutInflater.from(context);
			View msgView = mInflater.inflate(R.layout.commondialog_message,
					commondialog_contentlayout);
			TextView msg = (TextView) msgView
					.findViewById(R.id.commondialog_msg);
			msg.setText(Html.fromHtml(Msg));
			commondialog_contentlayout.setVisibility(View.VISIBLE);
		}
		return dialog;
	}

}
