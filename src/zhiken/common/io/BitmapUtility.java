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
	 * ���ݵ�ǰView������ͼ
	 * 
	 * @param filepath
	 * @return
	 */
	public static Bitmap decodeBitmap(String filepath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// ͨ�����bitmap��ȡͼƬ�Ŀ�͸�&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
		Bitmap bitmap = BitmapFactory.decodeFile(filepath, options);
		if (bitmap == null) {
			System.out.println("bitmapΪ��");
		}
		float realWidth = options.outWidth;
		float realHeight = options.outHeight;
		System.out.println("��ʵͼƬ�߶ȣ�" + realHeight + "���:" + realWidth);
		// �������ű�&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
		int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
		if (scale <= 0) {
			scale = 1;
		}
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		// ע�����Ҫ��options.inJustDecodeBounds ��Ϊ false,���ͼƬ��Ҫ��ȡ�����ġ�&nbsp;&nbsp;
		// &nbsp;&nbsp;&nbsp;
		bitmap = BitmapFactory.decodeFile(filepath, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println("����ͼ�߶ȣ�" + h + "���:" + w);
		return bitmap;
	}

//	/**
//	 * ���ݵ�ǰView������ͼ
//	 * 
//	 * @param filepath
//	 * @return
//	 */
//	public static Bitmap decodeBitmap(Bitmap bitmap) {
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		if (bitmap == null) {
//			System.out.println("bitmapΪ��");
//		}
//		float realWidth = options.outWidth;
//		float realHeight = options.outHeight;
//		System.out.println("��ʵͼƬ�߶ȣ�" + realHeight + "���:" + realWidth);
//		// �������ű�&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
//		int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
//		if (scale <= 0) {
//			scale = 1;
//		}
//		options.inSampleSize = scale;
//		options.inJustDecodeBounds = false;
//		
//		// ע�����Ҫ��options.inJustDecodeBounds ��Ϊ false,���ͼƬ��Ҫ��ȡ�����ġ�&nbsp;&nbsp;
//		// &nbsp;&nbsp;&nbsp;
//		bitmap = BitmapFactory.decodeFile(filepath, options);
//		int w = bitmap.getWidth();
//		int h = bitmap.getHeight();
//		System.out.println("����ͼ�߶ȣ�" + h + "���:" + w);
//		return bitmap;
//	}

	/**
	 * ��ͼƬ���Բ��
	 * 
	 * @param bitmap
	 *            ��Ҫ�޸ĵ�ͼƬ
	 * @param pixels
	 *            Բ�ǵĻ���
	 * @return Բ��ͼƬ
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
	 * ����Bitmap���ļ�ϵͳ
	 * 
	 * @param bitmap
	 *            Ҫ������ļ�
	 * @param savepath
	 *            Ҫ�����·��
	 * @return
	 * @throws IOException
	 */
	public static boolean save2file(Bitmap bitmap, String savepath) throws IOException {
		File file = new File(savepath);
		FileOutputStream out = new FileOutputStream(file);
		if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {// ��ʽ:PNG������:���;;PNG��ʽ������ģ���������������
			out.flush();
			out.close();
			return true;
		}
		return false;
	}
}
