package zhiken.common.os;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class ScreenHelper {
	private WakeLock mWakeLock;

	/**
	 * ȡ����
	 */
	public void acquireWakeLock(Context context) {
		if (mWakeLock == null) {
			// Logger.d("Acquiring wake lock");
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, this
					.getClass().getCanonicalName());
			mWakeLock.acquire();
		}
	}

	/**
	 * �ͷ���
	 */
	public void releaseWakeLock() {
		if (mWakeLock != null && mWakeLock.isHeld()) {
			mWakeLock.release();
			mWakeLock = null;
		}

	}

}

