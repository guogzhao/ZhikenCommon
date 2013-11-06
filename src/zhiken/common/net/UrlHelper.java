package zhiken.common.net;

import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.List;

import zhiken.common.model.KeyVal;

public class UrlHelper {
	public static String getUrlParamsEncode(List<KeyVal> lstKeyVal) {
		return getUrlParams(lstKeyVal, true);
	}

	public static String getUrlParamsString(List<KeyVal> lstKeyVal) {
		return getUrlParams(lstKeyVal, false);
	}

	private static String getUrlParams(List<KeyVal> lstKeyVal, boolean encode) {
		StringBuilder builder = new StringBuilder();
		if (lstKeyVal != null) {
			KeyVal keyval;
			for (int i = 0; i < lstKeyVal.size(); i++) {
				keyval = lstKeyVal.get(i);
				if (keyval.getVal() != null) {// 为null时不传
					if (builder.length() == 0) {
						builder.append("?");
					} else {
						builder.append("&");
					}
					builder.append(keyval.getKey());
					builder.append("=");
					if (encode) {
						builder.append(URLEncoder.encode(keyval.getVal()));
					} else {
						builder.append(keyval.getVal());
					}
				}
			}
		}
		return builder.toString();
	}
}
