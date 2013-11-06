package zhiken.common.util;

import android.util.Log;

/**
 * 日志打印工具�?
 * 
 * @author guogzhao 2012-03-20
 * 
 */
public class Logger {

	public static void o(String msg) {
		if (FLAG) {
			System.out.println(msg);
		}
	}

	public static void o(Exception e) {
		if (FLAG) {
			e.printStackTrace();
		}
	}

	private static final boolean FLAG = true;

	public static void v(String tag, String msg) {
		if (FLAG) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (FLAG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (FLAG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (FLAG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (FLAG) {
			Log.e(tag, msg);
		}
	}

	public String mTag;
	public boolean mFlag = false;

	public Logger(Class cls) {
		mTag = cls.getName();
	}

	public Logger(String tag) {
		mTag = tag;
	}

	public Logger(String tag, boolean flag) {
		mTag = tag;
		mFlag = flag;
	}

	public void v(String msg) {
		if (FLAG) {
			Log.v(mTag, msg);
		}
	}

	public void d(String msg) {
		if (FLAG) {
			Log.d(mTag, msg);
		}
	}

	public void i(String msg) {
		if (FLAG) {
			Log.i(mTag, msg);
		}
	}

	public void w(String msg) {
		if (FLAG) {
			Log.w(mTag, msg);
		}
	}

	public void e(String msg) {
		if (FLAG) {
			Log.e(mTag, msg);
		}
	}
}
