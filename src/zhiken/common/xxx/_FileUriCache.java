package zhiken.common.xxx;
//package zhiken.common.util;
//
//import java.util.HashMap;
//
//import zhiken.common.io.FileHelper;
//import zhiken.common.net.HttpHelper;
//import zhiken.common.net.HttpHelper.HttpCallBack;
//
//import android.net.Uri;
//
///**
// * 2013-03-10
// * 
// * @author guogzhao
// * 
// */
//public class FileUriCache implements HttpCallBack {
//	public interface FileLoadCallback {
//		public void onLoadCompleted(String url);
//	}
//
//	private FileLoadCallback mFileLoadCallback;
//
//	public void setFileLoadCallback(FileLoadCallback fileLoadCallback) {
//		this.mFileLoadCallback = fileLoadCallback;
//	}
//
//	private HttpHelper mHttpHelper;
//
//	private HttpHelper getHttpHelper() {
//		if (mHttpHelper == null) {
//			mHttpHelper = new HttpHelper();
//		}
//		return mHttpHelper;
//	}
//
//	private String mRootPath;
//	private HashMap<String, Uri> mUrlMap;
//
//	public FileUriCache(String rootpath) {
//		mRootPath = rootpath;
//		mUrlMap = new HashMap<String, Uri>();
//	}
//
//	public Uri getFileUriByHttpUrl(String url) {
//		// 判断存不存在,存在则返回；不存在则先添加，后加载,再返回
//		synchronized (mUrlMap) {
//			if (mUrlMap.containsKey(url)) {
//				return mUrlMap.get(url);
//			} else {
//				mUrlMap.put(url, null);
//			}
//		}
//
//		// 从文件系统里加载图片
//		Uri uri = null;
//		String filename = FileHelper.url2filename(url);
//		String filepath = FileHelper.getFilePath(mRootPath, filename);
//		// 如果文件存在
//		if (FileHelper.fileExists(filepath)) {
//			// 0.把文件路径转换为Uri
//			uri = Uri.parse(filepath);
//			// 1.添加该Uri到map中
//			mUrlMap.put(url, uri);
//		} else {// 不存在时，从网络上下载，下载好了后再回调
//			getHttpHelper().httpGetFileAsync(0, this, url, mRootPath, filename);
//		}
//		return uri;
//	}
//
//	@Override
//	public void httpCallBack(int requestCode, int rsponseStatus, String mssage,
//			String url, String filepath) {
//		// 0.把文件路径转换为Uri
//		Uri uri = Uri.parse(filepath);
//		// 1.添加该Uri到map中
//		mUrlMap.put(url, uri);
//
//		if (mFileLoadCallback != null) {
//			mFileLoadCallback.onLoadCompleted(url);
//		}
//	}
//}
//// try {
//// bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
//// mUrlMap.put(url, bitmap);
//// } catch (Exception e) {
//// e.printStackTrace();
//// }
//
//// private void setBitmap(ImageView img, String url) {
//// Bitmap bitmap = getBitmapByUrl(url);
//// if (bitmap != null) {
//// img.setImageBitmap(bitmap);
//// }
//// }
