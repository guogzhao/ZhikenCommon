package zhiken.common.io;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

@SuppressLint("NewApi")
public class DrawableDpi {
	/**
	 * ����ģʽ -����
	 */
	public static final int Level_Model_L3 = 3;
	/**
	 * ����ģʽ -����
	 */
	public static final int Level_Model_L4 = 4;
	/**
	 * ��ǰ����ģʽ
	 */
	private int mLevelModel = Level_Model_L3;// Ĭ�� ����ģʽ

	/**
	 * ���ü���ģʽ
	 * 
	 * @param levelModel
	 */
	public void setLevelModel(int levelModel) {
		this.mLevelModel = levelModel;
		this.initLevelModel();
	}

	/**
	 * ����ģʽ �ַ�
	 */
	public static final String[] mL3Chars = { "l", "m", "h" };

	/**
	 * ����ģʽ �ַ�
	 */
	public static final String[] mL4Chars = { "l", "m", "h", "x" };

	/**
	 * ��ǰ����ģʽ��Ӧ���ַ���
	 */
	private String[] mChars = mL3Chars;// Ĭ�ϼ���ģʽ���ַ���

	/**
	 * ���ü����ַ��б�
	 * 
	 * @param chars
	 */
	public void setLevelChars(String[] chars) {
		this.mChars = chars;
	}

	/**
	 * ��ǰ����
	 */
	private int mDpiLevel;

	private Activity mActivity;

	public DrawableDpi(Activity activity) {
		this.mActivity = activity;
		this.initLevelModel();
	}

	private int mDensityDpi;

	private void initLevelModel() {
		if (mDensityDpi == 0) {
			DisplayMetrics metric = new DisplayMetrics();
			this.mActivity.getWindowManager().getDefaultDisplay()
					.getMetrics(metric);
			mDensityDpi = metric.densityDpi;
		}

		if (mDensityDpi <= 120) {
			mDpiLevel = 0;// l
		} else if (mDensityDpi <= 180) {
			mDpiLevel = 1;// m
		} else {
			if (this.mLevelModel == Level_Model_L3) {
				mDpiLevel = 2;// h
			} else if (this.mLevelModel == Level_Model_L4) {
				if (mDensityDpi <= 260) {
					mDpiLevel = 2;// h
				} else {
					mDpiLevel = 3;// xh
				}
			}
		}
	}

	/**
	 * ��Assets��ȡͼƬ(��Ļ�ܶ����)
	 * 
	 * @param context
	 * @param filePrefixName
	 * @return
	 */
	public Bitmap getDpiBitmapFromAssets(String filePrefix, String fileSuffix) {
		return getBitmapFromAssets(getDpiFileName(filePrefix, fileSuffix));
	}

	/**
	 * ��Assets��ȡͼƬ
	 * 
	 * @param context
	 * @param filePrefixName
	 * @return
	 */
	public Bitmap getBitmapFromAssets(String filename) {
		Bitmap bitmap = null;
		try {
			AssetManager localAssetManager = this.mActivity.getAssets();
			InputStream localInputStream = localAssetManager.open(filename);
			bitmap = BitmapFactory.decodeStream(localInputStream);
			localInputStream.close();
		} catch (Exception ex) {
		}
		return bitmap;
	}

	/**
	 * ��Assets��ȡͼƬ(��Ļ�ܶ����)
	 * 
	 * @param context
	 * @param filePrefix
	 * @return
	 */
	public Bitmap getDpiBitmapFromAssets(String filePrefix) {
		return getDpiBitmapFromAssets(filePrefix, mFileSuffix);
	}

	/**
	 * Ĭ�Ϻ�׺
	 */
	private String mFileSuffix = "png";

	/**
	 * ��ȡ��Ļ�ܶ���ص��ļ���
	 * 
	 * @param filePrefix
	 * @param fileSuffix
	 * @return
	 */
	private String getDpiFileName(String filePrefix, String fileSuffix) {
		return filePrefix + mChars[mDpiLevel] + "." + fileSuffix;
	}

	// public String getDpiFileName(String filePrefixName) {
	// return filePrefixName + mChars[mDpiLevel] + "." + mFileSuffixName;
	// }
}
