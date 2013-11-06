package zhiken.common.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

public class IntentCommon {
	// public static void startActivity(Context ctx, Class<?> cls) {
	// Intent intent = new Intent();
	// intent.setClass(ctx, cls);
	// ctx.startActivity(intent);
	// }

	public static void startReOrderToFront(Context ctx, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(ctx, cls);
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		ctx.startActivity(intent);
	}

	public static void startForResult(Activity activity, Class<?> cls,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startForResult(Fragment fragment, Class<?> cls,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(fragment.getActivity(), cls);
		fragment.startActivityForResult(intent, requestCode);
	}

	public static void starActivityForUrl(Context context, String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		context.startActivity(intent);
	}

	public static void starActivityForTel(Context context, String tel) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + tel));
		context.startActivity(intent);
	}

	public static void starActivityForPackage(Context context,
			String packageName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + packageName));
		context.startActivity(intent);
	}
}
