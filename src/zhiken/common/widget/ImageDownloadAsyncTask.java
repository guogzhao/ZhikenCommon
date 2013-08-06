package zhiken.common.widget;

import java.io.File;

import zhiken.common.net.HttpHelper;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 图片异步（下载）显示类（ImageView,ListView）
 * 
 * @create 2013-06-08 12:56
 * @author guogzhao
 * 
 */
@SuppressLint("NewApi")
public class ImageDownloadAsyncTask extends AsyncTask<Object, Integer, Boolean> {
	public interface OnDownloadLinsener {
		public void onDownload(boolean isSuccess, ImageView imageView,
				String imageUrl, File imageFile, int imageResId,
				BaseAdapter imageAdapter);
	}

	private ImageView mImageView;
	private String mImageUrl;
	private File mImageFile;
	private int mImageResId;
	private BaseAdapter mImageAdapter;
	private OnDownloadLinsener mLinsener;

	public ImageDownloadAsyncTask(ImageView imageView, String imageUrl,
			File imageFile, int imageResId, BaseAdapter imageAdapter,
			OnDownloadLinsener linsener) {
		this.mImageView = imageView;
		this.mImageUrl = imageUrl;
		this.mImageFile = imageFile;
		this.mImageResId = imageResId;
		this.mImageAdapter = imageAdapter;
		this.mLinsener = linsener;
	}

	@Override
	protected Boolean doInBackground(Object... params) {
		// mUrl = (String) params[0];
		// mFile = (File) params[1];
		try {
			return HttpHelper.downloadFileSafe(mImageUrl, mImageFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	protected void onPostExecute(Boolean isSuccess) {
		if (mLinsener != null) {
			mLinsener.onDownload(isSuccess, mImageView, mImageUrl, mImageFile,
					mImageResId, mImageAdapter);
		}
	}
}