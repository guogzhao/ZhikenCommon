package zhiken.common.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @create 2013-07-16 10：39
 * @author guogzhao
 * 
 *         Android弹出拨号界面和拨打电话实现
 * 
 *         需要使用反射机制将ITelephony反射出来进行操作。
 */
public class TelephonyUtils {

	/**
	 * 弹出拨号界面
	 * 
	 * @param context
	 * @param number
	 */
	public static void dial(Context context, String number) {
		Class<TelephonyManager> c = TelephonyManager.class;
		Method getITelephonyMethod = null;
		try {
			getITelephonyMethod = c.getDeclaredMethod("getITelephony",
					(Class[]) null);
			getITelephonyMethod.setAccessible(true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			TelephonyManager tManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			Object iTelephony;
			iTelephony = (Object) getITelephonyMethod.invoke(tManager,
					(Object[]) null);
			Method dial = iTelephony.getClass().getDeclaredMethod("dial",
					String.class);
			dial.invoke(iTelephony, number);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 拨打电话实现
	 * 
	 * @param context
	 * @param number
	 */
	public static void call(Context context, String number) {
		Class<TelephonyManager> c = TelephonyManager.class;
		Method getITelephonyMethod = null;
		try {
			getITelephonyMethod = c.getDeclaredMethod("getITelephony",
					(Class[]) null);
			getITelephonyMethod.setAccessible(true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			TelephonyManager tManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			Object iTelephony;
			iTelephony = (Object) getITelephonyMethod.invoke(tManager,
					(Object[]) null);
			Method dial = iTelephony.getClass().getDeclaredMethod("call",
					String.class);
			dial.invoke(iTelephony, number);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
