package zhiken.common.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import zhiken.common.app.SDCardHelper;
import zhiken.common.io.StreamHelper;
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

	/**
	 * 发送HTTP POST请求
	 * 
	 * @param url
	 * @param entitys
	 * @return
	 */
	public static String post(String url, MultipartEntity entitys) {
		String result = null;
		try {
			// 根据内容来源地址创建一个Http请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(entitys);

			HttpParams httpParams = httpPost.getParams();
			{
				// 设置请求超时
				int timeoutConnection = 7 * 1000;// 7秒发出请求
				HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);

				// 设置响应超时
				int timeoutSocket = 8 * 1000;// 8秒获得响应
				HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);
			}

			// 发送请求并获取反馈
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
			// DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpPost);

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// public static String post(String url, MultipartEntity entitys) {
	// String result = null;
	// try {
	// HttpPost httpPost = new HttpPost(url);
	// httpPost.setEntity(entitys);
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpResponse httpResponse = httpclient.execute(httpPost);
	// if (httpResponse.getStatusLine().getStatusCode() == 200) {
	// result = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
	// }
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// } catch (ClientProtocolException e) {
	// e.printStackTrace();
	// } catch (ParseException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return result;
	// }

	public static MultipartEntity getMultipartEntity() {
		return new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	}

	// /**
	// *
	// * 获取HTTP请求响应的字符串
	// *
	// * @param url
	// * @param connTimeout
	// * @param readTimeout
	// * @return
	// * @throws Exception
	// **/
	// public static String getString(String url, int connTimeout, int
	// readTimeout) throws Exception {
	// HttpURLConnection conn = openConnection(url);
	// conn.setConnectTimeout(connTimeout);
	// conn.setReadTimeout(readTimeout);
	// conn.setRequestMethod(REQUEST_METHOD_GET);// 设置请求方式
	// if (conn.getResponseCode() == STATUS_OK) {// 如果请求成功
	// return StreamHelper.readStream(conn.getInputStream());
	// }
	// return null;
	// }

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
		// conn.setCharacterEncoding(charset);
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