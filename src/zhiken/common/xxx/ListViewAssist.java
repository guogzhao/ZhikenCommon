package zhiken.common.xxx;
//package zhiken.common.widget;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ListView;
//import cn.starwill.android.R;
//
//public class ListViewAssist {
//	private static String tag = ListViewAssist.class.getName();
//
//	public interface OnStateChangedLinsener {
//
//		public void onRefresh();
//
//		public void onLoading(int limit, int offset);
//	}
//
//	private static final int PAGE_SIZE = 10;
//	private static final int BEGIN_PAGE_INDEX = 0;
//	private int mCurrentPageIndex = BEGIN_PAGE_INDEX;
//	private ListView mListView;
//	private View mRefreshView;
//	private View mLoadingView;
//	private OnStateChangedLinsener mOnStateChangedLinsener;
//
//	public ListViewAssist(ListView listView, OnStateChangedLinsener linsener) {
//		mListView = listView;
//		mListView.setOnScrollListener(mOnScrollListener);
//		// mListView.setOnTouchListener(mOnTouchListener);
//
//		mOnStateChangedLinsener = linsener;
//
//		LayoutInflater inflater = LayoutInflater.from(listView.getContext());
//
//		// refresh
//		mRefreshView = inflater.inflate(R.layout.list_refresh, null);
//		mListView.addHeaderView(mRefreshView, null, false);
//		// initRefreshView();
//		// loading
//		mLoadingView = inflater.inflate(R.layout.list_loading, null);
//		mListView.addFooterView(mLoadingView, null, false);
//
//	}
//
//	public int getPageSize() {
//		return PAGE_SIZE;
//	}
//
//	public boolean isFirstPage() {
//		return (mCurrentPageIndex == BEGIN_PAGE_INDEX);
//	}
//
//	public void onLoadingCompleted() {
//		mLoadingView.setVisibility(View.GONE);
//	}
//
//	private OnScrollListener mOnScrollListener = new OnScrollListener() {
//
//		@Override
//		public void onScrollStateChanged(AbsListView view, int scrollState) {
//			switch (scrollState) {
//			case SCROLL_STATE_IDLE:
//				Log.e(tag, "SCROLL_STATE_IDLE");
//				break;
//			case SCROLL_STATE_FLING:
//				Log.e(tag, "SCROLL_STATE_FLING");
//				break;
//			case SCROLL_STATE_TOUCH_SCROLL:
//				Log.e(tag, "SCROLL_STATE_TOUCH_SCROLL");
//				break;
//			}
//		}
//
//		@Override
//		public void onScroll(AbsListView view, int firstVisibleItem,
//				int visibleItemCount, int totalItemCount) {
//			Log.e(tag, "first:" + firstVisibleItem + ",visible:"
//					+ visibleItemCount + ",total:" + totalItemCount);
//			// mFirstVisibleItemIndex = firstVisibleItem;
//
//			// 到底自动分页加载
//			if ((totalItemCount > 0)
//					&& (firstVisibleItem + visibleItemCount == totalItemCount)) {// 显示到最后一项时，翻页(下一页)
//				int nextPageIndex = totalItemCount / PAGE_SIZE;// 索引从0开始算
//				if (mCurrentPageIndex != nextPageIndex) {
//					mCurrentPageIndex++;
//					mLoadingView.setVisibility(View.VISIBLE);
//					// if (mOnLoadingLinsener != null)
//					mOnStateChangedLinsener.onLoading(PAGE_SIZE, PAGE_SIZE
//							* mCurrentPageIndex);
//				}
//			}
//		}
//	};
//
//	// // 释放
//	// private final static int TOUCH_STATE_RELEASE = 0;
//	// // 下拉
//	// private final static int TOUCH_STATE_PULL = 1;
//	// // 正在刷新
//	// private final static int TOUCH_STATE_REFRESHING = 2;
//	// // 完成
//	// private final static int TOUCH_STATE_FINISH = 3;
//	//
//	// private final static int LOADING = 4;
//	//
//	// private final static int RATIO = 3;
//	//
//	// private RefreshHolder mRefreshHolder;
//	//
//	// private class RefreshHolder {
//	// TextView toast;
//	// TextView lasttime;
//	// ImageView arrow;
//	// ProgressBar progress;
//	// }
//	//
//	// private void initRefreshView() {
//	// mRefreshHolder = new RefreshHolder();
//	// {
//	// mRefreshHolder.arrow = (ImageView) mRefreshView
//	// .findViewById(R.id.list_refresh_arrow);
//	// mRefreshHolder.arrow.setMinimumWidth(70);
//	// mRefreshHolder.arrow.setMinimumHeight(50);
//	//
//	// mRefreshHolder.progress = (ProgressBar) mRefreshView
//	// .findViewById(R.id.list_refresh_progress);
//	//
//	// mRefreshHolder.toast = (TextView) mRefreshView
//	// .findViewById(R.id.list_refresh_toast);
//	//
//	// mRefreshHolder.lasttime = (TextView) mRefreshView
//	// .findViewById(R.id.list_refresh_lasttime);
//	// }
//	// measureView(mRefreshView);
//	//
//	// headContentHeight = mRefreshView.getMeasuredHeight();
//	// headContentWidth = mRefreshView.getMeasuredWidth();
//	// mRefreshView.setPadding(0, -1 * headContentHeight, 0, 0);
//	// mRefreshView.invalidate();
//	//
//	// mRotateAnimation = new RotateAnimation(0, -180,
//	// RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//	// RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//	// mRotateAnimation.setInterpolator(new LinearInterpolator());
//	// mRotateAnimation.setDuration(250);
//	// mRotateAnimation.setFillAfter(true);
//	//
//	// mReverseAnimation = new RotateAnimation(-180, 0,
//	// RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//	// RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//	// mReverseAnimation.setInterpolator(new LinearInterpolator());
//	// mReverseAnimation.setDuration(200);
//	// mReverseAnimation.setFillAfter(true);
//	//
//	// mTouchState = TOUCH_STATE_FINISH;
//	// isRefreshable = false;
//	// }
//	//
//	// private RotateAnimation mRotateAnimation;
//	// private RotateAnimation mReverseAnimation;
//	// private boolean isRecored;
//	// private int headContentWidth;
//	// private int headContentHeight;
//	// private int startY;
//	// private int mFirstVisibleItemIndex;
//	// private int mTouchState;
//	// private boolean isBack;
//	// private boolean isRefreshable;
//	// private OnTouchListener mOnTouchListener = new OnTouchListener() {
//	// @Override
//	// public boolean onTouch(View v, MotionEvent event) {
//	// if (isRefreshable) {
//	// switch (event.getAction()) {
//	// case MotionEvent.ACTION_DOWN:
//	// if (mFirstVisibleItemIndex == 0 && !isRecored) {
//	// isRecored = true;
//	// startY = (int) event.getY();
//	// }
//	// break;
//	// case MotionEvent.ACTION_UP:
//	// if (mTouchState != TOUCH_STATE_REFRESHING
//	// && mTouchState != LOADING) {
//	// if (mTouchState == TOUCH_STATE_FINISH) {
//	// }
//	// if (mTouchState == TOUCH_STATE_PULL) {
//	// mTouchState = TOUCH_STATE_FINISH;
//	// changeHeaderViewByState();
//	// }
//	// if (mTouchState == TOUCH_STATE_RELEASE) {
//	// mTouchState = TOUCH_STATE_REFRESHING;
//	// changeHeaderViewByState();
//	// // onRefresh();
//	// mOnStateChangedLinsener.onRefresh();
//	// }
//	// }
//	// isRecored = false;
//	// isBack = false;
//	// break;
//	// case MotionEvent.ACTION_MOVE:
//	// int tempY = (int) event.getY();
//	// if (!isRecored && mFirstVisibleItemIndex == 0) {
//	// isRecored = true;
//	// startY = tempY;
//	// }
//	// if (mTouchState != TOUCH_STATE_REFRESHING && isRecored
//	// && mTouchState != LOADING) {
//	// if (mTouchState == TOUCH_STATE_RELEASE) {
//	// mListView.setSelection(0);
//	// if (((tempY - startY) / RATIO < headContentHeight)
//	// && (tempY - startY) > 0) {
//	// mTouchState = TOUCH_STATE_PULL;
//	// changeHeaderViewByState();
//	// } else if (tempY - startY <= 0) {
//	// mTouchState = TOUCH_STATE_FINISH;
//	// changeHeaderViewByState();
//	// }
//	// }
//	// if (mTouchState == TOUCH_STATE_PULL) {
//	// mListView.setSelection(0);
//	// if ((tempY - startY) / RATIO >= headContentHeight) {
//	// mTouchState = TOUCH_STATE_RELEASE;
//	// isBack = true;
//	// changeHeaderViewByState();
//	// } else if (tempY - startY <= 0) {
//	// mTouchState = TOUCH_STATE_FINISH;
//	// changeHeaderViewByState();
//	// }
//	// }
//	// if (mTouchState == TOUCH_STATE_FINISH) {
//	// if (tempY - startY > 0) {
//	// mTouchState = TOUCH_STATE_PULL;
//	// changeHeaderViewByState();
//	// }
//	// }
//	// if (mTouchState == TOUCH_STATE_PULL) {
//	// mRefreshView.setPadding(0, -1 * headContentHeight
//	// + (tempY - startY) / RATIO, 0, 0);
//	// }
//	// if (mTouchState == TOUCH_STATE_RELEASE) {
//	// mRefreshView.setPadding(0, (tempY - startY) / RATIO
//	// - headContentHeight, 0, 0);
//	// }
//	// }
//	// break;
//	// }
//	// }
//	// return false;
//	// }
//	// };
//	//
//	// private void changeHeaderViewByState() {
//	// switch (mTouchState) {
//	// case TOUCH_STATE_RELEASE:
//	// mRefreshHolder.arrow.setVisibility(View.VISIBLE);
//	// mRefreshHolder.progress.setVisibility(View.GONE);
//	// mRefreshHolder.toast.setVisibility(View.VISIBLE);
//	// mRefreshHolder.lasttime.setVisibility(View.VISIBLE);
//	// mRefreshHolder.arrow.clearAnimation();
//	// mRefreshHolder.arrow.startAnimation(mRotateAnimation);
//	// mRefreshHolder.toast.setText("请释放 刷新");
//	// break;
//	// case TOUCH_STATE_PULL:
//	// mRefreshHolder.progress.setVisibility(View.GONE);
//	// mRefreshHolder.toast.setVisibility(View.VISIBLE);
//	// mRefreshHolder.lasttime.setVisibility(View.VISIBLE);
//	// mRefreshHolder.arrow.clearAnimation();
//	// mRefreshHolder.arrow.setVisibility(View.VISIBLE);
//	// if (isBack) {
//	// isBack = false;
//	// mRefreshHolder.arrow.clearAnimation();
//	// mRefreshHolder.arrow.startAnimation(mReverseAnimation);
//	// mRefreshHolder.toast.setText("isBack  is true ！！！");
//	// } else {
//	// mRefreshHolder.toast.setText("isBack  is false ！！！");
//	// }
//	// break;
//	// case TOUCH_STATE_REFRESHING:
//	// mRefreshView.setPadding(0, 0, 0, 0);
//	// mRefreshHolder.progress.setVisibility(View.VISIBLE);
//	// mRefreshHolder.arrow.clearAnimation();
//	// mRefreshHolder.arrow.setVisibility(View.GONE);
//	// mRefreshHolder.toast.setText("正在加载中 ...REFRESHING");
//	// mRefreshHolder.lasttime.setVisibility(View.VISIBLE);
//	// break;
//	// case TOUCH_STATE_FINISH:
//	// mRefreshView.setPadding(0, -1 * headContentHeight, 0, 0);
//	// mRefreshHolder.progress.setVisibility(View.GONE);
//	// mRefreshHolder.arrow.clearAnimation();
//	// mRefreshHolder.arrow
//	// .setImageResource(R.drawable.list_refresh_arrow);
//	// mRefreshHolder.toast.setText("已经加载完毕- DONE ");
//	// mRefreshHolder.lasttime.setVisibility(View.VISIBLE);
//	// break;
//	// }
//	// }
//	//
//	// public void onRefreshCompleted() {
//	// mTouchState = TOUCH_STATE_FINISH;
//	// mRefreshHolder.lasttime
//	// .setText("已加载完成: " + new Date().toLocaleString());
//	// changeHeaderViewByState();
//	// }
//	//
//	// private void measureView(View child) {
//	// // ViewGroup.LayoutParams p = child.getLayoutParams();
//	// // if (p == null) {
//	// // p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//	// // ViewGroup.LayoutParams.WRAP_CONTENT);
//	// // }
//	// // int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
//	// // int lpHeight = p.height;
//	// // int childHeightSpec;
//	// // if (lpHeight > 0) {
//	// // childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
//	// // MeasureSpec.EXACTLY);
//	// // } else {
//	// // childHeightSpec = MeasureSpec.makeMeasureSpec(0,
//	// // MeasureSpec.UNSPECIFIED);
//	// // }
//	// // child.measure(childWidthSpec, childHeightSpec);
//	// }
//	//
//	// // public void setAdapter(BaseAdapter adapter) {
//	// // lastUpdatedTextView.setText("this is in MyListView:"
//	// // + new Date().toLocaleString());
//	// // super.setAdapter(adapter);
//	// // }
//}
