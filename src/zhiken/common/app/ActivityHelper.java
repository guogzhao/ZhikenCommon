package zhiken.common.app;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityHelper {

	/**
	 * @create 2013-07-18 16:18
	 * 
	 * @author guogzhao
	 * 
	 * 
	 *         �������ȫ������������
	 * 
	 *         ��Ҫ��super.onCreate(savedInstanceState)ǰ���ã�
	 * 
	 * @param activity
	 */
	public static void onCreateFullScreen(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
