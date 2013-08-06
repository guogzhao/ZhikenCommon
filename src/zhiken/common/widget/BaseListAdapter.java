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
 * 基础列表适配类（需拷贝到项目代码中使用）
 * 
 * @create 2013-06-09 13:53
 * @author guogzhao
 * 
 * @param <Holder>
 * @param <Item>
 */
public abstract class BaseListAdapter<Holder, Item> extends BaseAdapter {
	private Activity mActivity;
	private Fragment mFragment;
	private Context mContext;
	private LayoutInflater mInflater;
	private List<Item> mItemList = new ArrayList<Item>();
	private Holder mHolder;

	private int mItemLayoutId;

	public BaseListAdapter(Activity activity, int itemLayoutId) {
		mActivity = activity;
		init(activity, itemLayoutId);
	}

	public BaseListAdapter(Fragment fragment, int itemLayoutId) {
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

	public void setItemList(List<Item> listItem) {
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
			mHolder = (Holder) convertView.getTag();
		}

		setHolderView(position, mHolder, mItemList.get(position));

		return convertView;
	}

	// public abstract class Holder {
	// }

	protected abstract Holder getHolder(View v);

	protected abstract void setHolderView(int position, Holder holder, Item item);

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
