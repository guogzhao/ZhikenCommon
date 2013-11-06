package zhiken.common.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import zhiken.common.model.KeyVal;

public class ListUtil {
	public static Hashtable<String, String> toHashtable(List<KeyVal> lstKeyVal) {
		int size = lstKeyVal.size();
		if (size > 0) {
			Hashtable<String, String> result = new Hashtable<String, String>();
			KeyVal keyval;
			for (int i = 0; i < size; i++) {
				keyval = lstKeyVal.get(i);
				if (keyval.getVal() != null) {
					result.put(keyval.getKey(), keyval.getVal());
				}
			}
		}
		return null;
	}

	public static List<KeyVal> tableToList(Hashtable<String, String> table) {
		if (!table.isEmpty()) {
			List<KeyVal> lstKeyVal = new ArrayList<KeyVal>();
			String val;
			for (String key : table.keySet()) {
				val = table.get(key);
				if (val != null) {
					lstKeyVal.add(new KeyVal(key, val));
				}
			}
		}
		return null;
	}
}
