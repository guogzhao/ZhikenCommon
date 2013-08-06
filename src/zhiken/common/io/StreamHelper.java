package zhiken.common.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @create
 * @update 2013-06-19 15:00:47
 * @author guogzhao
 * 
 */
public class StreamHelper {

	/**
	 * 拷贝字节到输出流
	 * 
	 * @param byteArray
	 * @param out
	 * @throws IOException
	 */
	public static void copyStream(byte[] byteArray, OutputStream out) throws IOException {
		// int bufferSize = 1024;
		// for (int i = 0; i < byteArray.length; i += bufferSize) {
		// if ((i + bufferSize) < byteArray.length)
		// out.write(byteArray, i, bufferSize);
		// else {
		// out.write(byteArray, i, byteArray.length - i - 1);
		// }
		// }
		out.write(byteArray);
		out.close();
	}

	/**
	 * 拷贝输入流到输出流
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * 读取流到字符串
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String readStream(InputStream in) throws IOException {
		return readStream(in, null);
	}

	/**
	 * 读取流到字符串
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String readStream(InputStream in, Charset charset) throws IOException {
		StringBuffer out = new StringBuffer();// 线程安全
		{
			if (charset == null)
				charset = Charset.defaultCharset();

			// 输出对象
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, charset));

			String strInput = null;
			while ((strInput = bufferedReader.readLine()) != null) {
				out.append(strInput);
			}
		}
		return out.toString();
	}
}
// /**
// * 读取流到字符串
// *
// * @param in
// * @return
// * @throws IOException
// */
// public static String readStream(InputStream in) throws IOException {
// StringBuffer strResult = new StringBuffer();
// // 输出对象
// BufferedReader bufferedReader = new BufferedReader(new
// InputStreamReader(in));
//
// String strInput = null;
// while ((strInput = bufferedReader.readLine()) != null) {
// strResult.append(strInput);
// }
// in.close();
// return strResult.toString();
// }
// /**
// * 读取流到字节数组
// *
// * @param in
// * @return
// * @throws IOException
// */
// public static byte[] readStream(InputStream in) throws IOException {
// ByteArrayOutputStream out = new ByteArrayOutputStream();
// byte[] buffer = new byte[1024];
// int len = 0;
//
// while ((len = in.read(buffer)) != -1) {
// out.write(buffer, 0, len);
// }
// in.close();
// out.close();
// return out.toByteArray();
// }
// public static String readStream(InputStream in) throws IOException {
// StringBuffer out = new StringBuffer();
//
// // 输出对象
// BufferedReader bufferedReader = new BufferedReader(
// new InputStreamReader(in));
//
// String strInput = null;
// while ((strInput = bufferedReader.readLine()) != null) {
// out.append(strInput);
// }
// return out.toString();
// }
