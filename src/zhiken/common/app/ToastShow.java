package zhiken.common.app;

import android.content.Context;
import android.widget.Toast;

public class ToastShow {
	public static void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void show(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	public static void showLong(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
