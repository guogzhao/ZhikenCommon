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
//		// �жϴ治����,�����򷵻أ�������������ӣ������,�ٷ���
//		synchronized (mUrlMap) {
//			if (mUrlMap.containsKey(url)) {
//				return mUrlMap.get(url);
//			} else {
//				mUrlMap.put(url, null);
//			}
//		}
//
//		// ���ļ�ϵͳ�����ͼƬ
//		Uri uri = null;
//		String filename = FileHelper.url2filename(url);
//		String filepath = FileHelper.getFilePath(mRootPath, filename);
//		// ����ļ�����
//		if (FileHelper.fileExists(filepath)) {
//			// 0.���ļ�·��ת��ΪUri
//			uri = Uri.parse(filepath);
//			// 1.��Ӹ�Uri��map��
//			mUrlMap.put(url, uri);
//		} else {// ������ʱ�������������أ����غ��˺��ٻص�
//			getHttpHelper().httpGetFileAsync(0, this, url, mRootPath, filename);
//		}
//		return uri;
//	}
//
//	@Override
//	public void httpCallBack(int requestCode, int rsponseStatus, String mssage,
//			String url, String filepath) {
//		// 0.���ļ�·��ת��ΪUri
//		Uri uri = Uri.parse(filepath);
//		// 1.��Ӹ�Uri��map��
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
