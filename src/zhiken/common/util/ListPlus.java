package zhiken.common.util;

import java.util.ArrayList;

public abstract class ListPlus extends ArrayList {
	public Object[] toArray() {
		Object[] result = new Object[size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = get(i);
		}
		return result;
	}

	public void addArray(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}
	}
}
