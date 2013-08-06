package zhiken.common.os;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppsHelper {

	public static boolean exists(Context context, String pakgename) {

		// 获取所有应用
		PackageManager manager = context.getPackageManager();
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		final List<ResolveInfo> apps = manager.queryIntentActivities(
				mainIntent, 0);
		// //排序
		// Collections.sort(apps, new
		// ResolveInfo.DisplayNameComparator(manager));

		final int count = apps.size();
		ResolveInfo info = null;
		for (int i = 0; i < count; i++) {
			info = apps.get(i);
			if (pakgename.equals(info.activityInfo.packageName)) {
				return true;
			}
			// info.activityInfo.applicationInfo.packageName;
		}
		return false;
	}

	/**
	 * 获取应用版本
	 * 
	 * @return
	 */
	public static int getVersionCode(Context context) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionCode;
	}

}
