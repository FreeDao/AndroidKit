package com.hh.kit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * ���ַ�ʽ��װ�������
 * 
 */
public class ApkInstaller {

	/**
	 * @Description: ����������û�а�װ�������ְ�װ��ʽ�� 1.ֱ�Ӵ����������������ҳ�棬�������غ�װ��
	 *               2.�ѷ������apk��װ������assets��
	 *               ��Ϊ�˱��ⱻ����ѹ�����޸ĺ�׺��Ϊmp3��Ȼ��copy��SDcard�н��а�װ��
	 * @param context
	 * @param type
	 *            type=0,��ת�������url����;type=1,��assets��װassetsApk
	 * @param url
	 *            �������ַ,type=0ʱ��Ч,����ʱ���Ϊ��
	 * @param assetsApk
	 *            �ļ�����,type=1ʱ��Ч,����ʱ���Ϊ��
	 */
	public static void apkInstall(Context context, int type, String url,
			String assetsApk) {
		switch (type) {
		case 0:// ֱ�����ط�ʽ
			ApkInstaller.openDownloadWeb(context, url);
			break;
		case 1:// ���ذ�װ��ʽ
			if (!ApkInstaller.installFromAssets(context, assetsApk)) {
				Toast.makeText(context, "��װʧ��", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * ��assets�з������apk����SDcard�У�����SDcord�а�װ�������apk
	 * 
	 * @param context
	 * @param assetsApk
	 */
	public static boolean installFromAssets(Context context, String assetsApk) {
		try {
			AssetManager assets = context.getAssets();
			// ��ȡassets��ԴĿ¼�µ�SpeechService_1.0.1006.mp3,ʵ������SpeechService_1.0.1006.apk,Ϊ�˱��ⱻ����ѹ�����޸ĺ�׺����
			InputStream stream;
			stream = assets.open(assetsApk);
			if (stream == null) {
				Toast.makeText(context, "assets no apk", Toast.LENGTH_SHORT)
						.show();
				return false;
			}

			String folder = "/mnt/sdcard/Android/data";
			File f = new File(folder);
			if (!f.exists()) {
				f.mkdir();
			}

			String apkPath = "/mnt/sdcard/Android/data/SpeechService.apk";
			File file = new File(apkPath);
			// ��SDcard��д�ļ������Ȩ��
			// <uses-permission
			// android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
			if (!writeStreamToFile(stream, file)) {
				return false;
			}
			// ��װapk�ļ������Ȩ��
			// <uses-permission
			// android:name="android.permission.INSTALL_PACKAGES" />
			installApk(context, apkPath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * �����������������ҳ�档
	 * 
	 * @param context
	 * @param url
	 */
	public static void openDownloadWeb(Context context, String url) {
		Uri uri = Uri.parse(url);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(it);
	}

	/**
	 * ����������д���ݵ�һ���ļ��С�
	 * 
	 * @param stream
	 * @param file
	 */
	private static boolean writeStreamToFile(InputStream stream, File file) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			final byte[] buffer = new byte[1024];
			int read;
			while ((read = stream.read(buffer)) != -1) {
				output.write(buffer, 0, read);
			}
			output.flush();
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		} finally {
			try {
				output.close();
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * ����apk·����װapk����
	 * 
	 * @param context
	 * @param apkPath
	 */
	private static void installApk(Context context, String apkPath) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(apkPath)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
