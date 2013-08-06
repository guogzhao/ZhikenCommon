package zhiken.common.xxx;

import zhiken.common.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

@SuppressLint("NewApi")
public class PopupWindowDialog {
	public interface OnDestroyListener {
		public void onDestroy(PopupWindowDialog ppdialog);
	}

	public PopupWindowDialog(Activity activity) {
		mContext = activity;
	}

	private Activity mContext;

	public Activity getContext() {
		return mContext;
	}

	public void setContext(Activity context) {
		this.mContext = context;
	}

	private PopupWindow mPopupWindow;

	public PopupWindow getPopupWindow() {
		return mPopupWindow;
	}

	protected OnDestroyListener mOnDestroyListener;

	public OnDestroyListener getOnDestroyListener() {
		return mOnDestroyListener;
	}

	public void setOnDestroyListener(OnDestroyListener mOnDestroyListener) {
		this.mOnDestroyListener = mOnDestroyListener;
	}

	// private boolean mIsShow;

	private View mContentView;

	public View getContentView() {
		return mContentView;
	}

	/**
	 * 设置PopupWindow要显示的内容View
	 */
	public View setContentView(int resId) {
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		return setContentView(inflater.inflate(resId, null));
	}

	/**
	 * 设置PopupWindow要显示的内容View
	 */
	public View setContentView(View contentView) {
		mContentView = contentView;
		// 设置外触摸关闭事件处理
		mContentView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float x = event.getX();
				float y = event.getY();
				if (x < mContentView.getLeft() || x > mContentView.getRight()
						|| y < mContentView.getTop()
						|| y > mContentView.getBottom()) {
					destroy();
					return mContext.onTouchEvent(event);
				}
				return true;
			}
		});
		return mContentView;
	}

	@SuppressLint("NewApi")
	protected void checkInitView() {
		if (mPopupWindow == null) {
			// if (mIsFullScreen) {
			// mPopupWindow = new PopupWindow(mContentView,
			// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			// } else {
			mPopupWindow = new PopupWindow(mContentView,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			// }
			mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimationFade);
			mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦
			mPopupWindow.setTouchable(true); // 设置PopupWindow可触
			mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触
													// 在onTouch事件中根据触摸点是否在区域内来判
		}
	}

	public void showAtLocation(View parent) {
		showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	public void showAtLocation(View parent, int gravity, int x, int y) {
		checkInitView();
		mPopupWindow.showAtLocation(parent, gravity, x, y);

	}

	public void showAsDropDown(View anchor) {
		showAsDropDown(anchor, 0, 0);
	}

	public void showAsDropDown(View anchor, int xoff, int yoff) {
		checkInitView();
		mPopupWindow.showAsDropDown(anchor, xoff, yoff);
	}

	// 显示
	// // if (mIsShow = !mIsShow) {
	// if (!isShowing()) {
	// } else {
	// // 隐藏
	// destroy();
	// }
	// // 显示// if (mIsShow = !mIsShow) {
	// if (!isShowing()) {} else {
	// // 隐藏
	// destroy();
	// }
	public void update() {
		mPopupWindow.update();
	}

	public boolean isShowing() {
		if (null != mPopupWindow && mPopupWindow.isShowing()) {
			return true;
		}
		return false;
	}

	private long mDestroyTimeMillis;

	public boolean isShowingDelay(int timeMillis) {
		if (isShowing())
			return true;
		return System.currentTimeMillis() - mDestroyTimeMillis < timeMillis;
	}

	public void destroy() {
		if (null != mPopupWindow && mPopupWindow.isShowing()) {
			mPopupWindow.setFocusable(false);
			mPopupWindow.dismiss();
			mPopupWindow = null;

			onDestroy();
		}
	}

	private void onDestroy() {
		if (mOnDestroyListener != null) {
			mOnDestroyListener.onDestroy(this);
		}
		// 更新延迟显示状态
		mDestroyTimeMillis = System.currentTimeMillis();
	}
}

// public void show() {
// mIsFullScreen = false;
// View parent = mActivity.getWindow().getDecorView();
// showAtLocation(parent, Gravity.CENTER, 0, 0);
// }
//
// private boolean mIsFullScreen;
//
// public void showFullScreen() {
// mIsFullScreen = true;
// show();
// }
