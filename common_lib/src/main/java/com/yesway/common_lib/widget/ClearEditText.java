package com.yesway.common_lib.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.yesway.common_lib.R;

/**
 * 清除内容输入框
 * 
 * @author guojingbu
 */
@SuppressLint("NewApi")
public class ClearEditText extends AppCompatEditText implements TextWatcher, OnFocusChangeListener {
	/**
	 * 删除按钮
	 */
	private Drawable mRightDrawable;

	public ClearEditText(Context context) {
		super(context);
		init(context);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		// 获取删除按钮
		Drawable[] compoundDrawables = getCompoundDrawables();
		mRightDrawable = compoundDrawables[2];
		if (mRightDrawable == null) {
			mRightDrawable = ContextCompat.getDrawable(context, R.drawable.clear_edit_text_icon);
		}

		// 添加输入监听
		addTextChangedListener(this);
		// 添加焦点监听
		setOnFocusChangeListener(this);
		// 初始化不可见删除按钮
		setRightDrawableVisible(false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:

			int drawableLeft = getWidth() - getTotalPaddingRight();
			int drawableRight = getWidth() - getPaddingRight();

			int actionX = (int) event.getX();
			boolean cleanFlag = (actionX > drawableLeft && actionX < drawableRight) ? true : false;

			if (cleanFlag) {
				setText("");

				if (mOnTextClearListener != null) {
					mOnTextClearListener.onTextClear();
				}

			}

			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		boolean visible = getText().toString().trim().length() >= 1;
		setRightDrawableVisible(visible);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// 失去焦点
		if (!hasFocus) {
			setRightDrawableVisible(false);
		} else {
			boolean isVisible = getText().toString().length() >= 1;
			setRightDrawableVisible(isVisible);
		}
	}

	/**
	 * 设置删除按钮是否可见
	 * 
	 * @param visible
	 */
	protected void setRightDrawableVisible(boolean visible) {
		Drawable rightDrawable;
		if (visible) {
			rightDrawable = mRightDrawable;
		} else {
			rightDrawable = null;
		}
		setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], rightDrawable,
				getCompoundDrawables()[3]);
	}

	private OnTextClearListener mOnTextClearListener;

	public void setOnTextClearListener(OnTextClearListener listener) {
		mOnTextClearListener = listener;
	}

	/**
	 * 清除文本监听
	 *
	 * @author zhangke
	 */
	public interface OnTextClearListener {
		void onTextClear();
	}

}