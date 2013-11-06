package zhiken.common.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * @update 2013-07-29 15:37
 * @author guogzhao
 * 
 */
public class StringFormat {
	private static String sDecimal2Parten = "#.##";
	private static DecimalFormat sDecimal2Format = new DecimalFormat(sDecimal2Parten);

	public static String getDecimal2(double d) {
		return sDecimal2Format.format(d);
	}

	private static final String sInteger2Parten = "00";
	private static DecimalFormat sInteger2Format = new DecimalFormat(sInteger2Parten);

	public static String getInteger2(long l) {
		return sInteger2Format.format(l);
	}

	public static String padLeft(String right, int tatolLength, char ch) {
		StringBuilder stb = new StringBuilder(tatolLength);
		for (int i = right.length(); i < tatolLength; i++) {
			stb.append(ch);
		}
		stb.append(right);

		return stb.toString();
	}

	public static String padRight(String left, int tatolLength, char ch) {
		StringBuilder stb = new StringBuilder(tatolLength);
		stb.append(left);
		for (int i = left.length(); i < tatolLength; i++) {
			stb.append(ch);
		}
		return stb.toString();
	}

	// public static getString(double d, int c) {
	// String parten = "#.##";
	//
	// DecimalFormat decimal = new DecimalFormat(parten);
	// String str = decimal.format(你要格式化的东西);
	// }

	// public static GeoPoint getGeoPoint(String geopoint) {
	// String[] strs = geopoint.split(",");
	// return new GeoPoint(Integer.parseInt(strs[0]),
	// Integer.parseInt(strs[1]));
	// }
	//
	// public static String getGeoPoint(GeoPoint geopoint) {
	// return geopoint.getLatitudeE6() + "," + geopoint.getLongitudeE6();
	// }

	/**
	 * 字符串数组转换为指定分隔符的字符串
	 * 
	 * @create 2013-07-29 15：36
	 * @param lstStr
	 * @param delimiter
	 * @return
	 */
	public static String listToString(List<String> lstStr, String delimiter) {
		int size = lstStr.size();
		StringBuilder stbResult = new StringBuilder();
		for (int i = 0; i < size; i++) {
			stbResult.append(lstStr.get(i));
			if (i < size - 1) {
				stbResult.append(delimiter);
			}
		}
		return stbResult.toString();
	}

	/**
	 * 指定分隔符的字符串转换为字符串数组
	 * 
	 * @param strData
	 * @param delimiter
	 * @return
	 */
	public static List<String> stringToList(String strData, String delimiter) {
		String[] strs = strData.split(delimiter);
		List<String> lstResult = new ArrayList<String>();
		for (int i = 0; i < strs.length; i++) {
			lstResult.add(strs[i]);
		}
		return lstResult;
	}

	/**
	 * 显示友好的文件大小
	 * 
	 * @param size
	 * @return
	 */
	public String formatSize(long size) {
		String suffix = null;
		float fSize = 0;

		if (size >= 1024) {
			suffix = "KB";
			fSize = size / 1024;
			if (fSize >= 1024) {
				suffix = "MB";
				fSize /= 1024;
			}
			if (fSize >= 1024) {
				suffix = "GB";
				fSize /= 1024;
			}
		} else {
			fSize = size;
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
		if (suffix != null)
			resultBuffer.append(suffix);
		return resultBuffer.toString();
	}

	public static String format(String format, Object... params) {
		String[] formas = format.split("\\?");
		StringBuilder stringBuilder = new StringBuilder();// StringBuffer
		int size = Math.max(formas.length, params.length);
		for (int i = 0; i < size; i++) {
			stringBuilder.append(i < formas.length ? formas[i] : "");
			stringBuilder.append(i < params.length ? params[i] : "");
		}
		return stringBuilder.toString();
	}

	public static String format(Context context, int formatResId, Object... params) {
		return format(context.getString(formatResId), params);
	}
}
