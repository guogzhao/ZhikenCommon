package zhiken.common.encrypt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类
 * 
 * @author George
 * 
 */
public class MD5 {
	/**
	 * 32位的加密
	 * 
	 * @param text
	 * @return
	 */ 
	public static String get(String text) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();
			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	/**
	 * 16位的加密
	 * 
	 * @param text
	 * @return
	 */
	public static String get16(String text) {
		return get(text).substring(8, 24);
	}

	/**
	 * 32位的加密
	 * 
	 * @param file
	 * @return
	 */
	public static String getXmd5(File file) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = null;

		int index;
		try {

			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new ByteArrayOutputStream();

			byte[] b = new byte[bis.available()];
			while ((index = bis.read(b, 0, b.length)) != -1) {
				bos.write(b, 0, index);
			}
		} catch (FileNotFoundException e) {
			bos = null;
			e.printStackTrace();
		} catch (IOException e) {
			bos = null;
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (bos != null) {
			byte[] bosb = bos.toByteArray();
			for (int i = 0; i < 1024; i++) {
				bosb[i] = 0;
			}
			return get(new String(bosb));
		}
		return null;
	}
}