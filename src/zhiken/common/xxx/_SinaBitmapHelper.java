//package zhiken.common.xxx;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import android.graphics.BitmapFactory;
//import android.graphics.Rect;
//
///**
// * 新浪微博BitmapHelper
// * 
// * @author guogzhao
// * 
// */
//public final class _SinaBitmapHelper {//
//
//	public static boolean makesureSizeNotTooLarge(Rect rect) {
//		int FIVE_M = 5242880;
//
//		return rect.width() * rect.height() * 2 <= 5242880;
//	}
//
//	public static int getSampleSizeOfNotTooLarge(Rect rect) {
//		int FIVE_M = 5242880;
//		double ratio = rect.width() * rect.height() * 2.0D / 5242880.0D;
//		return (ratio >= 1.0D) ? (int) ratio : 1;
//	}
//
//	public static int getSampleSizeAutoFitToScreen(int vWidth, int vHeight,
//			int bWidth, int bHeight) {
//		if ((vHeight == 0) || (vWidth == 0)) {
//			return 1;
//		}
//
//		int ratio = Math.max(bWidth / vWidth, bHeight / vHeight);
//
//		int ratioAfterRotate = Math.max(bHeight / vWidth, bWidth / vHeight);
//
//		return Math.min(ratio, ratioAfterRotate);
//	}
//
//	public static boolean verifyBitmap(byte[] datas) {
//		return verifyBitmap(new ByteArrayInputStream(datas));
//	}
//
//	public static boolean verifyBitmap(InputStream input) {
//		if (input == null) {
//			return false;
//		}
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		input = (input instanceof BufferedInputStream) ? input
//				: new BufferedInputStream(input);
//		BitmapFactory.decodeStream(input, null, options);
//		try {
//			input.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return (options.outHeight > 0) && (options.outWidth > 0);
//	}
//
//	public static boolean verifyBitmap(String path) {
//		try {
//			return verifyBitmap(new FileInputStream(path));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//}
