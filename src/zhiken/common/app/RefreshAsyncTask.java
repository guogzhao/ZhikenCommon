package zhiken.common.app;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.BaseAdapter;

/**
 * 延迟更新列表
 * 
 * @create 2013-06-09 13:03
 * @author guogzhao
 * 
 */
@SuppressLint("NewApi")
public class RefreshAsyncTask extends AsyncTask<Integer, Integer, Boolean> {
	private BaseAdapter mBaseAdapter;

	public RefreshAsyncTask(BaseAdapter adapter) {
		mBaseAdapter = adapter;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		mBaseAdapter.notifyDataSetChanged();
	}

	@Override
	protected Boolean doInBackground(Integer... params) {
		try {
			if (params != null && params.length > 0) {
				Thread.sleep(params[0]);
			} else {
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
}
