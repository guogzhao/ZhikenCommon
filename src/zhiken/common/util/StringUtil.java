package zhiken.common.util;

import java.io.NotSerializableException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.TextView;

/**
 * @create 2013-08-29 11:27
 * @author guogzhao
 * 
 */
public class StringUtil {
	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";

	/**
	 * NULL
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null)
			return true;

		return false;
	}

	/**
	 * NULL/空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null)
			return true;

		if (str.equals(""))
			return true;

		return false;
	}

	/**
	 * NULL/空字符串/纯空格的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrWhiteSpace(String str) {
		if (str == null)
			return true;

		if (str.equals(""))
			return true;

		if (str.trim().equals(""))
			return true;

		return false;
	}

	public static String toString(String... values) {
		StringBuilder stb = new StringBuilder();
		for (String value : values) {
			if (value != null) {
				stb.append(value);
			}
		}
		return stb.toString();
	}

	/**
	 * 正则表达式
	 * 
	 * @param str
	 * @param regexen
	 * @return
	 * @throws NotSerializableException
	 */
	public static boolean isMatch(String str, String regexen) throws NotSerializableException {
		throw new NotSerializableException();
	}

	/**
	 * 正则验证邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// public static boolean isEmail(String strEmail) {／／春数字邮箱名不能过
	// String strPattern =
	// "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
	//
	// Pattern p = Pattern.compile(strPattern);
	// Matcher m = p.matcher(strEmail);
	// return m.matches();
	// }

	public static String URLEncoderSafe(String str) {
		if (isNullOrEmpty(str))
			return str;
		// return " ";
		else
			return URLEncoder.encode(str);
	}

	// public static String URLEncoder(String str, String charset) {
	// if (isNullOrEmpty(str))
	// return str;
	// else
	// return URLEncoder.encode(str, charset);
	// }
	
//	public static String Ignore case(String str) {
//		str.equalsIgnoreCase(string)
//	}
}
