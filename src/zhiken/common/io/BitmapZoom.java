package zhiken.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;

public class BitmapZoom {

	// ����ͼƬ
	public static Bitmap zoomImg(String img, int newWidth, int newHeight) {
		// ͼƬԴ
		Bitmap bm = BitmapFactory.decodeFile(img);
		if (null != bm) {
			return zoomImg(bm, newWidth, newHeight);
		}
		return null;
	}

	public static Bitmap zoomImg(Context context, String img, float newWidth, float newHeight) {
		// ͼƬԴ
		try {
			Bitmap bm = BitmapFactory.decodeStream(context.getAssets().open(img));
			if (null != bm) {
				return zoomImg(bm, newWidth, newHeight);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ����ͼƬ
	public static Bitmap zoomImg(Bitmap bm, float newWidth, float newHeight) {
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
}