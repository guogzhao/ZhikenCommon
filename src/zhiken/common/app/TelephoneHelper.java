package zhiken.common.app;

import android.app.Service;
import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephoneHelper {

	public static String getLine1Number(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Service.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}
}
