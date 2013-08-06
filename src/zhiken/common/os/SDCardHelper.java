package zhiken.common.os;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SDCardHelper extends BroadcastReceiver {

	/**
	 * SD Card is available a temporary variable
	 */
	private static boolean mSDCardAvailabe;

	/**
	 * SD Card is available detection status
	 */
	private static boolean mSDCardAvailabilityDetected;

	/**
	 * 
	 * @return SD is available ?
	 */
	public static synchronized boolean detectSDCardAvailability() {
		boolean flag = false;

		try {

			// ggz:test sd card has write
			File file = new File("/sdcard/" + UUID.randomUUID());
			flag = file.createNewFile();
			file.delete();

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			mSDCardAvailabilityDetected = true;
			mSDCardAvailabe = flag;
		}
		return flag;
	}

	/**
	 * 
	 * @return SD is available ?
	 */
	public static boolean isSDCardAvailable() {
		if (!mSDCardAvailabilityDetected) {
			mSDCardAvailabe = detectSDCardAvailability();
			mSDCardAvailabilityDetected = true;
		}
		return mSDCardAvailabe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	public void onReceive(Context context, Intent intent) {// ggz:w这里会不会存在SD广播
															// 接通了,但监测时还没好...
		mSDCardAvailabilityDetected = false;
		mSDCardAvailabe = detectSDCardAvailability();
		mSDCardAvailabilityDetected = true;
	}
}

// <receiver android:name=".SDCardHelper">
// <intent-filter>
// <action android:name="android.intent.action.MEDIA_EJECT" />
// <action android:name="android.intent.action.MEDIA_MOUNTED" />
// <action android:name="android.intent.action.MEDIA_REMOVED" />
// <action android:name="android.intent.action.MEDIA_UNMOUNTED" />
// <data android:scheme="file" />
// </intent-filter>
// </receiver>  