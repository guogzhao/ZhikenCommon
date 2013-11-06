package zhiken.common.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;

public class NetHelper {
	private static ConnectivityManager sConnectivityManager;

	/**
	 * ��ǰ�����Ƿ����
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (sConnectivityManager == null) {
			sConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
		}

		if (sConnectivityManager.getActiveNetworkInfo() != null) {
			return sConnectivityManager.getActiveNetworkInfo().isAvailable();
		}
		return false;
	}

	/**
	 * ����������ҳ��
	 * 
	 * @param context
	 */
	public static void startActivityWirelessSettings(Context context) {
		Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);// Settings.ACTION_SETTINGS
		context.startActivity(intent);
	}

	/**
	 * �������ûص�RESULTֵ
	 */
	public static final int ACTIVITY_RESULT_WIRELESS_SETTINGS = 0X127;

	/**
	 * ����������ҳ�棬���ص�
	 * 
	 * @param context
	 */
	public static void startActivityForResultWirelessSettings(Activity context) {
		Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);// Settings.ACTION_SETTINGS
		context.startActivityForResult(intent,
				ACTIVITY_RESULT_WIRELESS_SETTINGS);
	}

}
