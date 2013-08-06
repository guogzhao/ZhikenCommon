package zhiken.common.xxx;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;

public class ResourcesUri {
	public static Uri res2Uri(String pageageName, int resId) {
		Uri uri = Uri.parse("android.resource://" + pageageName + "/" + resId);
		return uri;
	}

	public static Uri res2Uri(Resources res, int resId) {
		Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
				+ res.getResourcePackageName(resId) + "/"
				+ res.getResourceTypeName(resId) + "/"
				+ res.getResourceEntryName(resId));
		return uri;
	}

//	public static Uri asset2Uri(String assetPath) {
//		return Uri.parse("file:///android_asset/" + assetPath);
//	}

	// Uri uri = Uri.parse("android.resource://"+ pageageName + "/" +
	// resId);
	// android.resource://cn.starwill.xy/drawable-hdpi/2130837522
	// 搞定了 谢谢谢谢 Uri uri = Uri.parse("android.resource://"+ getPackageName()
	// +"/"+ R.raw.video11); videoView1.setVideoURI(uri); 代码是这样的才对
}
