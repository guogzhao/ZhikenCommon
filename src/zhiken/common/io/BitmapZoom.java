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

	// 缩放图片
	public static Bitmap zoom(String imgpath, int newWidth, int newHeight) {
		// 图片源
		Bitmap bm = BitmapFactory.decodeFile(imgpath);
		if (null != bm) {
			return zoom(bm, newWidth, newHeight);
		}
		return null;
	}

	public static Bitmap zoom(Context context, String imgpath, float newWidth, float newHeight) {
		// 图片源
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

	// 缩放图片
	public static Bitmap zoom(Bitmap bm, float newWidth, float newHeight) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = newWidth / width;
		float scaleHeight = newHeight / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		return newbm;
	}

	// 缩放图片
	public static Bitmap zoom(Bitmap bm, float newWidth) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = newWidth / width;
		float scaleHeight = scaleWidth;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		return newbm;
	}

	// /**
	// * 缩放图片
	// *
	// * @param bm
	// * @param newWidth
	// * @param newHeight
	// * @return
	// */
	// public static Bitmap zoom(Bitmap bm, int newWidth, int newHeight) {
	// // 获得图片的宽高
	// int width = bm.getWidth();
	// int height = bm.getHeight();
	// // 计算缩放比例
	// float scaleWidth = ((float) newWidth) / width;
	// float scaleHeight = ((float) newHeight) / height;
	// // 取得想要缩放的matrix参数
	// Matrix matrix = new Matrix();
	// matrix.postScale(scaleWidth, scaleHeight);
	// // 得到新的图片
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
		// // 得到新的图片
		// Bitmap nBitmap = Bitmap.createBitmap(oBitmap, 0, 0, oW, oH, matrix,
		// true);
		//
		// imageView.setBackground(new BitmapDrawable(nBitmap));
	}

	/**
	 * 计算根据新宽度和最大高度得到新的高度
	 * 
	 * @param bm
	 * @param newWidth
	 * @param maxNewHeight
	 * @return
	 */
	public static float getNewHeightByNewWidth(Bitmap bm, int newWidth, int maxHeight) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;

		// 得到新的高度
		float newHeight = scaleWidth * height;

		// 返回比较小的尺寸
		return Math.min(newHeight, maxHeight);
	}
}