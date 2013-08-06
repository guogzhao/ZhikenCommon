package zhiken.common.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DPIHelper {
	// /**
	// * 级别模式四-字符集
	// */
	// public static final String[] mL4Chars = { "l", "m", "h", "x" };

	private Activity mActivity;

	// /**
	// * 设备实际的DPI
	// */
	// private int mRealDensityDpi;
	// /**
	// * 程序使用的DPI
	// */
	// private int mUsedDensityDpi;
	private float mDensity;

	public DPIHelper(Activity activity) {
		mActivity = activity;
		mDensity = mActivity.getResources().getDisplayMetrics().density;
	}

	// /**
	// * 返回当前设备对应的DPI
	// *
	// * @return
	// */
	// public int getDensityDpi() {
	// return mUsedDensityDpi;
	// }

	// public static final int DPI_LOW = 120;
	// public static final int DPI_MEDIUM = 160;
	// public static final int DPI_HIGH = 240;
	// public static final int DPI_XHIGH = 320;
	// public static final int DPI_XXHIGH = 480;
	//
	// private void init(Activity activity) {
	// mActivity = activity;
	// if (mRealDensityDpi == 0) {
	// DisplayMetrics metric = new DisplayMetrics();
	// {
	// mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
	// }
	// mRealDensityDpi = metric.densityDpi;
	// }
	// if (mRealDensityDpi <= DPI_LOW + (DPI_MEDIUM - DPI_LOW) / 2) {
	// mUsedDensityDpi = DPI_LOW;
	// } else if (mRealDensityDpi <= DPI_MEDIUM + (DPI_HIGH - DPI_MEDIUM) / 2) {
	// mUsedDensityDpi = DPI_MEDIUM;
	// } else if (mRealDensityDpi <= DPI_HIGH + (DPI_XHIGH - DPI_HIGH) / 2) {
	// mUsedDensityDpi = DPI_HIGH;
	// } else if (mRealDensityDpi <= DPI_XHIGH + (DPI_XXHIGH - DPI_XHIGH) / 2) {
	// mUsedDensityDpi = DPI_XHIGH;
	// } else {
	// mUsedDensityDpi = DPI_XXHIGH;
	// }
	// }

	public Bitmap getBitmap(int resId) {

		Drawable drawable = mActivity.getResources().getDrawable(resId);
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		return bitmapDrawable.getBitmap();
	}

	public float hdpi2Pixel(float value) {
		return value / 1.5f * mDensity;
	}

	public float xhdpi2Pixel(float value) {
		return value / 2.0f * mDensity;
	}
	// Bitmap bmp = BitmapFactory.decodeResource(mActivity.Resources()get,
	// R.drawable.icon);
	// Bitmap newb = Bitmap.createBitmap(300, 300, Config.ARGB_8888);
	// Canvas canvasTemp = new Canvas(newb);
	// canvasTemp.drawBitmap(bmp, 50, 50, p);
}
// if (mDisplayMetricsDensityDpi <= 120) {
// mCurrentDPI = 120;
// } else if (mDisplayMetricsDensityDpi <= 160) {
// mCurrentDPI = 160;
// } else if (mDisplayMetricsDensityDpi <= 240) {
// mCurrentDPI = 240;
// } else if (mDisplayMetricsDensityDpi <= 320) {
// mCurrentDPI = 320;
// } else {// if (mDisplayMetricsDensityDpi <= 480)
// mCurrentDPI = 480;
// }
