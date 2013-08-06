package zhiken.common.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ViewHelper {

	public static View getView(Context context, int resId) {
		return LayoutInflater.from(context).inflate(resId, null);
	}
}
