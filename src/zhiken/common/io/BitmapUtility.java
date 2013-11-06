package zhiken.common.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class BitmapUtility {
	/**
	 * 根据当前View的缩略图
	 * 
	 * @param filepath
	 * @return
	 */
	public static Bitmap decodeBitmap(String filepath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// 通过这个bitmap获取图片的宽和高&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
		Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
		if (bitmap == null) {
			System.out.println("bitmap为空");
		}
		float realWidth = options.outWidth;
		float realHeight = options.outHeight;
		System.out.println("真实图片高度：" + realHeight + "宽度:" + realWidth);
		// 计算缩放比&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
		int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
		if (scale <= 0) {
			scale = 1;
		}
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		// 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。&nbsp;&nbsp;
		// &nbsp;&nbsp;&nbsp;
		bitmap = BitmapFactory.decodeFile(filepath, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println("缩略图高度：" + h + "宽度:" + w);
		return bitmap;
	}

//	/**
//	 * 根据当前View的缩略图
//	 * 
//	 * @param filepath
//	 * @return
//	 */
//	public static Bitmap decodeBitmap(Bitmap bitmap) {
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		if (bitmap == null) {
//			System.out.println("bitmap为空");
//		}
//		float realWidth = options.outWidth;
//		float realHeight = options.outHeight;
//		System.out.println("真实图片高度：" + realHeight + "宽度:" + realWidth);
//		// 计算缩放比&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
//		int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
//		if (scale <= 0) {
//			scale = 1;
//		}
//		options.inSampleSize = scale;
//		options.inJustDecodeBounds = false;
//		
//		// 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。&nbsp;&nbsp;
//		// &nbsp;&nbsp;&nbsp;
//		bitmap = BitmapFactory.decodeFile(filepath, options);
//		int w = bitmap.getWidth();
//		int h = bitmap.getHeight();
//		System.out.println("缩略图高度：" + h + "宽度:" + w);
//		return bitmap;
//	}

	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 *            需要修改的图片
	 * @param pixels
	 *            圆角的弧度
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * 保存Bitmap到文件系统
	 * 
	 * @param bitmap
	 *            要保存的文件
	 * @param savepath
	 *            要保存的路径
	 * @return
	 * @throws IOException
	 */
	public static boolean save2file(Bitmap bitmap, String savepath) throws IOException {
		File file = new File(savepath);
		FileOutputStream out = new FileOutputStream(file);
		if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {// 格式:PNG，质量:最好;;PNG格式是无损的，将忽略质量设置
			out.flush();
			out.close();
			return true;
		}
		return false;
	}
}
