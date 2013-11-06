package zhiken.common.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5.class.getName() + "初始化失败，MessageDigest不支持MD5");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 得到文件的MD5
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String get(File file) throws IOException {// getFileMD5String
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	/**
	 * 得到字符串的MD5
	 * 
	 * @param s
	 * @return
	 */
	public static String get(String s) {
		return get(s.getBytes());
	}

	/**
	 * 得到字节数组的MD5
	 * 
	 * @param bytes
	 * @return
	 */
	public static String get(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = get(password);
		return s.equals(md5PwdStr);
	}

	// public static void main(String[] args) throws IOException {
	// long begin = System.currentTimeMillis();
	//
	// // 2EA3E66AC37DF7610F5BD322EC4FFE48 670M 11s kuri双核1.66G 2G内存
	// File big = new File("e:/新建文件夹.rar");
	//
	// String md5 = getFileMD5String(big);
	//
	// long end = System.currentTimeMillis();
	// System.out.println("md5:" + md5 + "time:" + ((end - begin) / 1000) +
	// "s");
	// }

}