package zhiken.common.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

@SuppressLint("NewApi")
public class AppCommon {
	public static void hideSoftInput(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	// public static void showSoftInput(Context context, View v) {
	// InputMethodManager imm = (InputMethodManager)
	// context.getSystemService(Context.INPUT_METHOD_SERVICE);
	// imm.showSoftInput(v, flags)
	// }
	public static Display getDefaultDisplay(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return windowManager.getDefaultDisplay();
		// // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
		// //int xPos = windowManager.getDefaultDisplay().getWidth() / 2 -
		// popupWindow.getWidth() / 2;
	}

	public static View inflate(Context context, int resId) {
		return LayoutInflater.from(context).inflate(resId, null);
	}

	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}
}
