package zhiken.common.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 基础列表适配类（无泛型，需转换）
 * 
 * @create 2013-06-09 13:53
 * @author guogzhao
 * 
 * @param <Object>
 * @param <Item>
 */
public abstract class BaseListObjectAdapter extends BaseAdapter {
	private Activity mActivity;
	private Fragment mFragment;
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Object> mItemList = new ArrayList<Object>();
	private Object mHolder;

	private int mItemLayoutId;

	public BaseListObjectAdapter(Activity activity, int itemLayoutId) {
		mActivity = activity;
		init(activity, itemLayoutId);
	}

	public BaseListObjectAdapter(Fragment fragment, int itemLayoutId) {
		mFragment = fragment;
		init(fragment.getActivity(), itemLayoutId);
	}

	private void init(Context context, int itemLayoutId) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		mItemLayoutId = itemLayoutId;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public Fragment getFragment() {
		return mFragment;
	}

	public Context getContext() {
		return mContext;
	}

	// protected abstract int getItemLayoutId();
	//
	// public void setItemLayoutId(int resId) {
	// mItemLayoutId = resId;
	// }

	public List<?> getItemList() {
		return mItemList;
	}

	public void setItemList(List<Object> listItem) {
		this.mItemList = listItem;
	}

	@Override
	public int getCount() {
		return mItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return mItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(mItemLayoutId, null);
			mHolder = getHolder(convertView);
			convertView.setTag(mHolder);
		} else {
			mHolder = (Object) convertView.getTag();
		}

		setHolderView(position, mHolder, mItemList.get(position));

		return convertView;
	}

	// public abstract class Holder {
	// }

	protected abstract Object getHolder(View v);

	protected abstract void setHolderView(int position, Object holder,
			Object item);

	protected class OnItemButtonClickListener implements View.OnClickListener {
		private int mPosition;

		public OnItemButtonClickListener(int position) {
			this.mPosition = position;
		}

		@Override
		public void onClick(View v) {
			onItemButtonClick(mPosition, v);
		}
	}

	protected abstract void onItemButtonClick(int position, View v);
}
