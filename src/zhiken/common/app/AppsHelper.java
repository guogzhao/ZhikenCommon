package zhiken.common.app;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class AppsHelper {

	public static boolean exists(Context context, String pakgename) {

		// 获取所有应用
		PackageManager manager = context.getPackageManager();
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		final List<ResolveInfo> apps = manager.queryIntentActivities(mainIntent, 0);
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
	 * 获取IMEI码(电话才有，平板没有)
	 * 
	 * @return　IMEI
	 */
	public String getDeviceId(Context context) {
		String imei = ((TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE)).getDeviceId();
		return imei;
	}

	/**
	 * 获取本机MAC地址
	 * 
	 * @return　MAC
	 */
	public String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String mac = info.getMacAddress();
		if (mac != null) {
			int i = 0;
			i++;
		}
		return mac;
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

	public static final String META_DATA_UMENG_CHANNEL = "UMENG_CHANNEL";
	public static final String META_DATA_UMENG_CHANNEL_DEBUG = "debug";

	public static String getUmengChannel(Context context) {
		String umengChannel = getMetaData(context, META_DATA_UMENG_CHANNEL);
		return umengChannel;
	}

	/**
	 * 得到meta-data
	 * 
	 * @param context
	 * @param metaName
	 * @return
	 */
	public static String getMetaData(Context context, String metaName) {
		try {
			ApplicationInfo appinfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			Bundle bundle = appinfo.metaData;
			if (bundle != null) {
				Object metaValue = bundle.get(metaName);
				if (metaValue != null) {
					return metaValue.toString();
				}
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
