package zhiken.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;

public class BitmapZoom {

	// ����ͼƬ
	public static Bitmap zoom(String imgpath, int newWidth, int newHeight) {
		// ͼƬԴ
		Bitmap bm = BitmapFactory.decodeFile(imgpath);
		if (null != bm) {
			return zoom(bm, newWidth, newHeight);
		}
		return null;
	}

	public static Bitmap zoom(Context context, String imgpath, float newWidth, float newHeight) {
		// ͼƬԴ
		try {
			Bitmap bm = BitmapFactory.decodeStream(context.getAssets().open(imgpath));
			if (null != bm) {
				return zoom(bm, newWidth, newHeight);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ����ͼƬ
	public static Bitmap zoom(Bitmap bm, float newWidth, float newHeight) {
		// ���ͼƬ�Ŀ��
		int width = bm.getWidth();
		int height = bm.getHeight();
		// �������ű���
		float scaleWidth = newWidth / width;
		float scaleHeight = newHeight / height;
		// ȡ����Ҫ���ŵ�matrix����
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// �õ��µ�ͼƬ
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		return newbm;
	}

	// ����ͼƬ
	public static Bitmap zoom(Bitmap bm, float newWidth) {
		// ���ͼƬ�Ŀ��
		int width = bm.getWidth();
		int height = bm.getHeight();
		// �������ű���
		float scaleWidth = newWidth / width;
		float scaleHeight = scaleWidth;
		// ȡ����Ҫ���ŵ�matrix����
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// �õ��µ�ͼƬ
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		return newbm;
	}

	// /**
	// * ����ͼƬ
	// *
	// * @param bm
	// * @param newWidth
	// * @param newHeight
	// * @return
	// */
	// public static Bitmap zoom(Bitmap bm, int newWidth, int newHeight) {
	// // ���ͼƬ�Ŀ��
	// int width = bm.getWidth();
	// int height = bm.getHeight();
	// // �������ű���
	// float scaleWidth = ((float) newWidth) / width;
	// float scaleHeight = ((float) newHeight) / height;
	// // ȡ����Ҫ���ŵ�matrix����
	// Matrix matrix = new Matrix();
	// matrix.postScale(scaleWidth, scaleHeight);
	// // �õ��µ�ͼƬ
	// Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
	// true);
	// return newbm;
	// }

	public static void setImageToViewByWidth(ImageView imageView, File imageFile) {

		// imageView.setImageURI(Uri.fromFile(imageFile));
		Bitmap oBitmap = null;
		try {
			oBitmap = BitmapFactory.decodeStream(new FileInputStream(imageFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int x = imageView.getWidth();
		int oW = oBitmap.getWidth();
		int oH = oBitmap.getHeight();

		imageView.setImageBitmap(oBitmap);

		int iW = imageView.getWidth();
		int iH = imageView.getHeight();

		// // float nW = imageView.getMaxWidth();
		// // float nW2=imageView.getMeasuredWidth();
		// float nW = 480 - 16 * 1.5f * 2;
		// float scaleWidth = nW / oW;
		//
		Matrix matrix = new Matrix();
		// matrix.postScale(scaleWidth, scaleWidth);
		//
		// // �õ��µ�ͼƬ
		// Bitmap nBitmap = Bitmap.createBitmap(oBitmap, 0, 0, oW, oH, matrix,
		// true);
		//
		// imageView.setBackground(new BitmapDrawable(nBitmap));
	}

	/**
	 * ��������¿�Ⱥ����߶ȵõ��µĸ߶�
	 * 
	 * @param bm
	 * @param newWidth
	 * @param maxNewHeight
	 * @return
	 */
	public static float getNewHeightByNewWidth(Bitmap bm, int newWidth, int maxHeight) {
		// ���ͼƬ�Ŀ��
		int width = bm.getWidth();
		int height = bm.getHeight();
		// �������ű���
		float scaleWidth = ((float) newWidth) / width;

		// �õ��µĸ߶�
		float newHeight = scaleWidth * height;

		// ���رȽ�С�ĳߴ�
		return Math.min(newHeight, maxHeight);
	}
}