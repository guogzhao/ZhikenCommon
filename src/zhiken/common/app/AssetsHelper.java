package zhiken.common.app;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @create 2013-07-18 15:32
 * @author guogzhao
 * 
 *         本地资源帮助类
 */
public class AssetsHelper {

	/**
	 * 从Assets获取图片
	 * 
	 * @param context
	 * @param filePrefixName
	 * @return
	 */
	public static Bitmap getBitmapFromAssets(Context context, String filename) {
		Bitmap bitmap = null;
		try {
			AssetManager localAssetManager = context.getAssets();
			InputStream localInputStream = localAssetManager.open(filename);
			bitmap = BitmapFactory.decodeStream(localInputStream);
			localInputStream.close();
		} catch (Exception ex) {
		}
		return bitmap;
	}
}
