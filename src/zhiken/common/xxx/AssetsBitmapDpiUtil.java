package zhiken.common.xxx;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

public class AssetsBitmapDpiUtil {  //a
    /**
     * 级别模式 -三种
     */
    public static final int Level_Model_L3 = 3;
    /**
     * 级别模式 -四种
     */
    public static final int Level_Model_L4 = 4;
    /**
     * 当前级别模式
     */
    private int mLevelModel = Level_Model_L3;// 默认 级别模式�?

    /**
     * 设置级别模式
     * 
     * @param levelModel
     */
    public void setLevelModel(int levelModel) {
        this.mLevelModel = levelModel;
        this.initLevelModel();
    }

    /**
     * 级别模式�?字符�?
     */
    public static final String[] mL3Chars = { "l", "m", "h" };

    /**
     * 级别模式�?字符�?
     */
    public static final String[] mL4Chars = { "l", "m", "h", "x" };

    /**
     * 当前级别模式对应的字符集
     */
    private String[] mChars = mL3Chars;// 默认级别模式三字符集

    /**
     * 设置级别字符列表
     * 
     * @param chars
     */
    public void setLevelChars(String[] chars) {
        this.mChars = chars;
    }

    /**
     * 当前级别
     */
    private int mDpiLevel;

    private Context mActivity;

    public AssetsBitmapDpiUtil(Context context) {
        this.mActivity = context;
        this.initLevelModel();
    }

    private int mDensityDpi;

    private void initLevelModel() {
        if (mDensityDpi == 0) {
            DisplayMetrics metric = new DisplayMetrics();
			((Activity) this.mActivity).getWindowManager().getDefaultDisplay()
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
     * 从Assets获取图片(屏幕密度相关)
     * 
     * @param context
     * @param filePrefixName
     * @return
     */
    public Bitmap getDpiBitmapFromAssets(String filePrefix, String fileSuffix) {
        return getBitmapFromAssets(getDpiFileName(filePrefix, fileSuffix));
    }

    /**
     * 从Assets获取图片
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
     * 从Assets获取图片(屏幕密度相关)
     * 
     * @param context
     * @param filePrefix
     * @return
     */
    public Bitmap getDpiBitmapFromAssets(String filePrefix) {
        return getDpiBitmapFromAssets(filePrefix, mFileSuffix);
    }

    /**
     * 默认后缀�?
     */
    private String mFileSuffix = "png";

    /**
     * 获取屏幕密度相关的文件名
     * 
     * @param filePrefix
     * @param fileSuffix
     * @return
     */
    private String getDpiFileName(String filePrefix, String fileSuffix) {
        return filePrefix + mChars[mDpiLevel] +"."+ fileSuffix;
    }

    // public String getDpiFileName(String filePrefixName) {
    // return filePrefixName + mChars[mDpiLevel] + "." + mFileSuffixName;
    // }
}
