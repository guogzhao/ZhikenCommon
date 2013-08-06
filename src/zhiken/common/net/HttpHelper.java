package zhiken.common.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import zhiken.common.io.StreamHelper;
import zhiken.common.os.SDCardHelper;
import android.content.Context;

public class HttpHelper {

	public static final int STATUS_OK = 200;
	public static final String REQUEST_METHOD_GET = "GET";
	public static final String REQUEST_METHOD_POST = "POST";

	/**
	 * 打开Http连接
	 * 
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private static HttpURLConnection openConnection(String url) throws MalformedURLException, IOException {
		return (HttpURLConnection) new URL(url).openConnection();
	}

	/**
	 * 
	 * 获取HTTP请求响应的字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 **/
	public static String getString(String url) throws Exception {
		HttpURLConnection conn = openConnection(url);
		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
			return StreamHelper.readStream(conn.getInputStream());
		}
		return null;
	}

	/**
	 * 
	 * 获取HTTP请求响应的字符串
	 * 
	 * @param url
	 * @param timeoutMillis
	 * @return
	 * @throws Exception
	 **/
	public static String getString(String url, int timeoutMillis) throws Exception {
		HttpURLConnection conn = openConnection(url);
		conn.setConnectTimeout(timeoutMillis);
		conn.setReadTimeout(timeoutMillis);
		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
			return StreamHelper.readStream(conn.getInputStream());
		}
		return null;
	}

//	/**
//	 * 
//	 * 获取HTTP请求响应的字符串
//	 * 
//	 * @param url
//	 * @param connTimeout
//	 * @param readTimeout
//	 * @return
//	 * @throws Exception
//	 **/
//	public static String getString(String url, int connTimeout, int readTimeout) throws Exception {
//		HttpURLConnection conn = openConnection(url);
//		conn.setConnectTimeout(connTimeout);
//		conn.setReadTimeout(readTimeout);
//		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
//		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
//			return StreamHelper.readStream(conn.getInputStream());
//		}
//		return null;
//	}

	/**
	 * 
	 * 获取HTTP请求响应的字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 **/
	public static String getString(String url, Charset charset) throws Exception {
		HttpURLConnection conn = openConnection(url);
		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
			return StreamHelper.readStream(conn.getInputStream(), charset);
		}
		return null;
	}

	/**
	 * 
	 * 获取HTTP请求响应的字符串
	 * 
	 * @param url
	 * @param charset
	 * @param timeoutMillis
	 * @return
	 * @throws Exception
	 **/
	public static String getString(String url, Charset charset, int timeoutMillis) throws Exception {
		HttpURLConnection conn = openConnection(url);
		conn.setConnectTimeout(timeoutMillis);
		conn.setReadTimeout(timeoutMillis);
		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
			return StreamHelper.readStream(conn.getInputStream(), charset);
		}
		return null;
	}

	// // String saveDir,
	// // String saveName
	public static boolean downloadFile(String url, File file) throws Exception {
		HttpURLConnection conn = openConnection(url);
		conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
		if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
			StreamHelper.copyStream(conn.getInputStream(), new FileOutputStream(file));
			return true;
		}
		return false;
	}

	public static boolean downloadFileSafe(String url, File file) throws Exception {

		// 生成临时文件
		File fileTemp = new File(file.getPath() + ".tmp");

		// 下载保存到临时文件
		try {
			if (HttpHelper.downloadFile(url, fileTemp)) {
				fileTemp.renameTo(file);// 重命名临时文件到目标文件
				return true;
			}

		} catch (Exception ex) {
			try {
				file.delete();// 若重命名出错，则删除下载文件
			} catch (Exception e) {
			}
			try {
				fileTemp.delete();
			} catch (Exception e) {
			}
			throw ex;
		}
		return false;
	}

	/**
	 * 监测网络和SD卡可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean downloadCheck(Context context) {

		return downloadCheck(context, null);
	}

	/**
	 * 监测网络和SD卡及保存目录可用（推荐）
	 * 
	 * @param context
	 * @return
	 */
	public static boolean downloadCheck(Context context, File saveDir) {
		if (NetHelper.isNetworkAvailable(context)) {// 网络可用
			if (SDCardHelper.isSDCardAvailable()) {// SD卡可用
				if (saveDir != null) {
					// 目录可用
					if (!saveDir.exists()) {
						saveDir.mkdirs();
					}
				}
				return true;
			}
		}
		return false;
	}
}
// /**
// *
// * 获取HTTP请求响应的字节数组
// *
// * @param url
// * @return
// * @throws Exception
// **/
// public static byte[] getByteArray(String url) throws Exception {
// HttpURLConnection conn = openConnection(url);
// conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
// if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
// return StreamHelper.readStream(conn.getInputStream());
// }
// return null;
// }
// /**
// *
// * 获取HTTP请求响应的字符串
// *
// * @param url
// * @return
// * @throws Exception
// **/
// public static String getString(String url) throws Exception {
// HttpURLConnection conn = openConnection(url);
// conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
// if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
// return StreamHelper.readStream(conn.getInputStream());
// }
// return null;
//
// // StringBuffer strResult = new StringBuffer();
// // InputStreamReader in = new InputStreamReader(conn.getInputStream());
// //
// // // 输出对象
// // BufferedReader bufferedReader = new BufferedReader(in);
// //
// // String strInput = null;
// // while ((strInput = bufferedReader.readLine()) != null) {
// // strResult.append(strInput);
// // }
// // return strResult.toString();
// }
// /**
// * HTTP 请求回调接口
// *
// * @author guogzhao
// *
// */
// public interface HttpCallBack {
// public void httpCallBack(int requestCode, int rsponseStatus,
// String mssage, String url, String filepath);
//
// }

// private boolean mIsRunning = true;

// public void httpGetFileAsync(final int requestId, final HttpCallBack hcb,
// final String url, final String saveDir, final String saveName) {
// new Thread() {
// public void run() {
// httpGetFile(requestId, hcb, url, saveDir, saveName);
// }
// }.start();
// }
//
// public static final int TIMEOUT = 15 * 1000;
// public void httpGetFile(int requestId, HttpCallBack hcb, String url,
// String saveDir, String saveName) {
// BufferedReader bufferedReader = null;
// HttpClient httpClient = null;
// HttpGet httpGet = null;
// int responseStatus = 0;
// try {
// // 检测目录（不存在则创建）
// File folderFile = new File(saveDir);
// if (!(folderFile.exists() && folderFile.isDirectory())) {
// folderFile.mkdirs();
// }
// if (!saveDir.endsWith("/")) {
// saveDir = saveDir + "/";
// }
// String savePath = saveDir + saveName;
// File file = new File(savePath);
// if (file.exists()) {
// file.delete();
// }
// file.createNewFile();
// // ～
//
// httpClient = new DefaultHttpClient();
// httpClient.getParams().setParameter(
// CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
//
// httpGet = new HttpGet();
// httpGet.setURI(new URI(url));
//
// HttpResponse httpResponse = httpClient.execute(httpGet);
// InputStream in = httpResponse.getEntity().getContent();
// responseStatus = httpResponse.getStatusLine().getStatusCode();
//
// RandomAccessFile raf = new RandomAccessFile(file, "rwd");
// byte[] b = new byte[1024];
// int offset = 0;
// while ((offset = in.read(b, 0, 1024)) != -1) {
// raf.write(b, 0, offset);
// }
// raf.close();
// in.close();
// if (mIsRunning) {
// // System.out.println("下载完成");
// hcb.httpCallBack(requestId, responseStatus, null, url, savePath);
// }
// } catch (Exception e) {
// hcb.httpCallBack(requestId, responseStatus, e.getMessage(), url,
// null);
// Logger.o(e);
// } finally {
// httpClient.getConnectionManager().shutdown();
// if (bufferedReader != null) {
// try {
// bufferedReader.close();
// bufferedReader = null;
// } catch (IOException e) {
// Logger.o(e);
// }
// }
// }
// }