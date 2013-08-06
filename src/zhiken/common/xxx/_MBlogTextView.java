package zhiken.common.xxx;

import android.content.Context;
import android.widget.TextView;

//package com.queque.widget;
//
//import android.content.Context;
//import android.text.Layout;
//import android.text.Spannable;
//import android.text.method.MovementMethod;
//import android.text.style.ClickableSpan;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.widget.TextView;
//
public class _MBlogTextView extends TextView {
	// private boolean a;
	//
	public _MBlogTextView(Context paramContext) {
		super(paramContext);
	}
	//
	// public MBlogTextView(Context paramContext, AttributeSet
	// paramAttributeSet) {
	// super(paramContext, paramAttributeSet);
	// }
	//
	// public MBlogTextView(Context paramContext, AttributeSet
	// paramAttributeSet,
	// int paramInt) {
	// super(paramContext, paramAttributeSet, paramInt);
	// }
	//
	// public boolean a(MotionEvent paramMotionEvent) {
	// int i = paramMotionEvent.getAction();
	// int i2;
	// int i5;
	// if ((i == 1) || (i == 0)) {
	// int j = (int) paramMotionEvent.getX();
	// int k = (int) paramMotionEvent.getY();
	// int l = j - getTotalPaddingLeft();
	// int i1 = k - getTotalPaddingTop();
	// i2 = l + getScrollX();
	// int i3 = i1 + getScrollY();
	// Layout localLayout = getLayout();
	// int i4 = localLayout.getLineForVertical(i3);
	// i5 = localLayout.getOffsetForHorizontal(i4, i2);
	// float f = localLayout.getLineWidth(i4);
	// if (i2 <= f)
	// break label206;
	// }
	// for (int i6 = i2;; i6 = i5) {
	// Spannable localSpannable;
	// if (getText() instanceof Spannable) {
	// localSpannable = (Spannable) getText();
	// ClickableSpan[] arrayOfClickableSpan = (ClickableSpan[]) localSpannable
	// .getSpans(i6, i6, ClickableSpan.class);
	// if ((arrayOfClickableSpan == null)
	// || (arrayOfClickableSpan.length == 0))
	// ;
	// }
	// label206: for (int i7 = 0;; i7 = 1)
	// while (true) {
	// return i7;
	// bc[] arrayOfbc = (bc[]) localSpannable.getSpans(i6, i6,
	// bc.class);
	// if ((arrayOfbc == null) || (arrayOfbc.length == 0))
	// break;
	// i7 = 0;
	// }
	// }
	// }
	//
	// public boolean onTouchEvent(MotionEvent paramMotionEvent) {
	// MovementMethod localMovementMethod = getMovementMethod();
	// CharSequence localCharSequence = getText();
	// if ((localMovementMethod != null)
	// && (localCharSequence instanceof Spannable)
	// && (a(paramMotionEvent))) {
	// localMovementMethod.onTouchEvent(this,
	// (Spannable) localCharSequence, paramMotionEvent);
	// if (!this.a)
	// ;
	// }
	// for (boolean bool = false;; bool = super.onTouchEvent(paramMotionEvent))
	// return bool;
	// }
	//
	// public boolean performLongClick() {
	// MovementMethod localMovementMethod = getMovementMethod();
	// if ((localMovementMethod != null)
	// && (localMovementMethod instanceof ea))
	// ((ea) localMovementMethod).a(this);
	// return super.performLongClick();
	// }
	//
	// public void setDispatchToParent(boolean paramBoolean) {
	// this.a = paramBoolean;
	// }
}