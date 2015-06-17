/**  
 * Copyright © 2013 WHTY Tech Co. Ltd. All rights reserved.
 *
 * @Title: Log.java
 * @Description: TODO
 * @author: Mr.Gdp  
 * @date: 2013年11月13日 下午12:17:53
 */
package com.hh.kit.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * @ClassName: Log
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月13日 下午12:17:53
 */
public class Log {

	private static final String MESSAGE_TEMPLATE = "[%s]%s";
	private static final Object sSyncObject = new Object();

	private static final Pattern sNewLinePattern = Pattern
			.compile("\r|\r\n|\n");
	public static final int ALL = -1;
	public static final int VERBOSE = 2;
	public static final int DEBUG = 3;
	public static final int INFO = 4;
	public static final int WARN = 5;
	public static final int ERROR = 6;
	public static final int ASSERT = 7;
	public static final int NONE = 2147483647;
	public static final PrintStream err = new AndroidPrintStream(6, "");

	public static final PrintStream systemErr = System.err;

	private static int sFilterLevel = -1;
	private static String sApplicationTag;

	static {
		if (System.err != err)
			System.setErr(err);
	}

	public static final void setApplicationTag(String tag) {
		sApplicationTag = tag;
	}

	public static final String getApplicationTag() {
		return sApplicationTag;
	}

	public static final void setFilterLevel(int level) {
		synchronized (sSyncObject) {
			sFilterLevel = level;
		}
	}

	public static void setLogLevel(String logLevel) {
		synchronized (sSyncObject) {
			if ("VERBOSE".equals(logLevel))
				sFilterLevel = 2;
			else if ("DEBUG".equals(logLevel))
				sFilterLevel = 3;
			else if ("INFO".equals(logLevel))
				sFilterLevel = 4;
			else if ("WARN".equals(logLevel))
				sFilterLevel = 5;
			else if ("ERROR".equals(logLevel))
				sFilterLevel = 6;
		}
	}

	public static final int getFilterLevel() {
		return sFilterLevel;
	}

	public static final String getDefaultTag(Class<?> cls) {
		if (cls == null) {
			return "";
		}
		return cls.getSimpleName();
	}

	public static int v(String tag, String msg) {
		return println(2, tag, msg);
	}

	public static int v(String tag, String format, Object[] args) {
		String msg = String.format(format, args);
		return println(2, tag, msg);
	}

	public static int v(String tag, String msg, Throwable tr) {
		return println(2, tag, msg + '\n' + getStackTraceString(tr));
	}

	public static int d(String tag, String msg) {
		return println(3, tag, msg);
	}

	public static int d(String tag, String format, Object[] args) {
		String msg = String.format(format, args);
		return println(3, tag, msg);
	}

	public static int d(String tag, String msg, Throwable tr) {
		return println(3, tag, msg + '\n' + getStackTraceString(tr));
	}

	public static int i(String tag, String msg) {
		return println(4, tag, msg);
	}

	public static int i(String tag, String format, Object[] args) {
		String msg = String.format(format, args);
		return println(4, tag, msg);
	}

	public static int i(String tag, String msg, Throwable tr) {
		return println(4, tag, msg + '\n' + getStackTraceString(tr));
	}

	public static int w(String tag, String msg) {
		return println(5, tag, msg);
	}

	public static int w(String tag, String format, Object[] args) {
		String msg = String.format(format, args);
		return println(5, tag, msg);
	}

	public static int w(String tag, String msg, Throwable tr) {
		return println(5, tag, msg + '\n' + getStackTraceString(tr));
	}

	public static boolean isLoggable(String tag, int level) {
		return android.util.Log.isLoggable(tag, level);
	}

	public static int w(String tag, Throwable tr) {
		return println(5, tag, getStackTraceString(tr));
	}

	public static int e(String tag, String format, Object[] args) {
		String msg = String.format(format, args);
		return println(6, tag, msg);
	}

	public static int e(String tag, String msg) {
		return println(6, tag, msg);
	}

	public static int e(String tag, String msg, Throwable tr) {
		int r = println(6, tag, msg + '\n' + getStackTraceString(tr));
		return r;
	}

	public static String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);
		return sw.toString();
	}

	public static int println(int priority, String tag, String msg) {
		String logTag;
		String format;
		String message = "";
		if ((priority < sFilterLevel) || (TextUtils.isEmpty(msg))) {
			return 0;
		}
		String[] messageLines = sNewLinePattern.split(msg);

		if ((TextUtils.isEmpty(sApplicationTag))
				|| (sApplicationTag.equals(tag))) {
			logTag = tag;
			format = null;
		} else if ((TextUtils.isEmpty(tag))
				&& (!(TextUtils.isEmpty(sApplicationTag)))) {
			logTag = sApplicationTag;
			format = null;
		} else {
			logTag = sApplicationTag;
			format = "[%s]%s";
		}

		int bytesWritten = 0;
		if (TextUtils.isEmpty(format)) {
			for (int i = 0; i < messageLines.length; i++) {
				bytesWritten += android.util.Log.println(priority, logTag,
						messageLines[i]);
			}
		} else {
			for (int i = 0; i < messageLines.length; i++) {
				message = String.format("[%s]%s", new Object[] { tag,
						messageLines[i] });
				bytesWritten += android.util.Log.println(priority, logTag,
						message);
			}
		}
		return bytesWritten;
	}

	public static void init(Context context, boolean debug) {
		setApplicationTag(generateApplicationTag(context));
		setFilterLevel((debug) ? -1 : 4);
	}

	public static String generateApplicationTag(Context context) {
		if (context != null) {
			PackageManager pm = context.getPackageManager();
			try {
				PackageInfo packageInfo = pm.getPackageInfo(
						context.getPackageName(), 0);
				ApplicationInfo applicationInfo = packageInfo.applicationInfo;
				String applicationTag = String.format("%s/%s", new Object[] {
						context.getString(applicationInfo.labelRes),
						packageInfo.versionName });
				return applicationTag;
			} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			}
		}
		return null;
	}

	public static String generateApplicationTagWithoutVersionName(
			Context context) {
		if (context != null) {
			ApplicationInfo applicationInfo = context.getApplicationInfo();
			String applicationTag = context.getString(applicationInfo.labelRes);
			return applicationTag;
		}
		return null;
	}

	static class AndroidPrintStream extends Log.LoggingPrintStream {
		private final int priority;
		private final String tag;

		public AndroidPrintStream(int priority, String tag) {
			if (tag == null) {
				throw new NullPointerException("tag");
			}

			this.priority = priority;
			this.tag = tag;
		}

		protected void log(String line) {
			Log.println(this.priority, this.tag, line);
		}
	}

	static abstract class LoggingPrintStream extends PrintStream {
		private final StringBuilder builder = new StringBuilder();

		private final Formatter formatter = new Formatter(this.builder, null);

		protected LoggingPrintStream() {
			super(new OutputStream() {
				public void write(int oneByte) throws IOException {
					throw new AssertionError();
				}
			});
		}

		protected abstract void log(String paramString);

		public synchronized void flush() {
			flush(true);
		}

		private void flush(boolean completely) {
			int nextBreak = 0;
			int length = this.builder.length();

			int start = 0;
			do {
				log(this.builder.substring(start, nextBreak));
				start = nextBreak + 1;

				if (start >= length)
					break;
			} while ((nextBreak = this.builder.indexOf("\n", start)) != -1);

			if (completely) {
				if (start < length) {
					log(this.builder.substring(start));
				}
				this.builder.setLength(0);
			} else {
				this.builder.delete(0, start);
			}
		}

		public void write(int oneByte) {
		}

		public void write(byte[] buffer) {
		}

		public void write(byte[] bytes, int start, int count) {
		}

		public boolean checkError() {
			return false;
		}

		protected void setError() {
		}

		public void close() {
		}

		public PrintStream format(String format, Object[] args) {
			return format(Locale.getDefault(), format, args);
		}

		public PrintStream printf(String format, Object[] args) {
			return format(format, args);
		}

		public PrintStream printf(Locale l, String format, Object[] args) {
			return format(l, format, args);
		}

		public synchronized PrintStream format(Locale l, String format,
				Object[] args) {
			if (format == null) {
				throw new NullPointerException("format");
			}

			this.formatter.format(l, format, args);
			flush(false);
			return this;
		}

		public synchronized void print(char[] charArray) {
			this.builder.append(charArray);
			flush(false);
		}

		public synchronized void print(char ch) {
			this.builder.append(ch);
			if (ch == '\n')
				flush(false);
		}

		public synchronized void print(double dnum) {
			this.builder.append(dnum);
		}

		public synchronized void print(float fnum) {
			this.builder.append(fnum);
		}

		public synchronized void print(int inum) {
			this.builder.append(inum);
		}

		public synchronized void print(long lnum) {
			this.builder.append(lnum);
		}

		public synchronized void print(Object obj) {
			this.builder.append(obj);
			flush(false);
		}

		public synchronized void print(String str) {
			this.builder.append(str);
			flush(false);
		}

		public synchronized void print(boolean bool) {
			this.builder.append(bool);
		}

		public synchronized void println() {
			flush(true);
		}

		public synchronized void println(char[] charArray) {
			this.builder.append(charArray);
			flush(true);
		}

		public synchronized void println(char ch) {
			this.builder.append(ch);
			flush(true);
		}

		public synchronized void println(double dnum) {
			this.builder.append(dnum);
			flush(true);
		}

		public synchronized void println(float fnum) {
			this.builder.append(fnum);
			flush(true);
		}

		public synchronized void println(int inum) {
			this.builder.append(inum);
			flush(true);
		}

		public synchronized void println(long lnum) {
			this.builder.append(lnum);
			flush(true);
		}

		public synchronized void println(Object obj) {
			this.builder.append(obj);
			flush(true);
		}

		public synchronized void println(String s) {
			if (this.builder.length() == 0) {
				int nextBreak = 0;
				int length = s.length();

				int start = 0;
				do {
					log(s.substring(start, nextBreak));
					start = nextBreak + 1;

					if (start >= length)
						break;
				} while ((nextBreak = s.indexOf(10, start)) != -1);

				if (start < length)
					log(s.substring(start));
			} else {
				this.builder.append(s);
				flush(true);
			}
		}

		public synchronized void println(boolean bool) {
			this.builder.append(bool);
			flush(true);
		}

		public synchronized PrintStream append(char c) {
			print(c);
			return this;
		}

		public synchronized PrintStream append(CharSequence csq) {
			this.builder.append(csq);
			flush(false);
			return this;
		}

		public synchronized PrintStream append(CharSequence csq, int start,
				int end) {
			this.builder.append(csq, start, end);
			flush(false);
			return this;
		}
	}
}
