package com.yesway.common_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.yesway.common_lib.R;

/**
 * @Description
 * @Author guojingbu
 * @Date 2019/3/5
 */
public class CommonItemView extends FrameLayout {
    /**
     * 默认字体样式
     */
    public static final int DEFAULT_TEXT_STYLE = Typeface.NORMAL;
    private TextView mTxtValue;
    private TextView mTxtName;
    private ImageView mIvArrow;
    private boolean isRightSingleLine = true;

    public CommonItemView(@NonNull Context context) {
        this(context, null);
    }

    public CommonItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CommonItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.item_common_layout, this);
        mTxtValue = findViewById(R.id.txt_value);
        mTxtName = findViewById(R.id.txt_name);
        mIvArrow = findViewById(R.id.iv_arrow);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonItemView);
        String leftText = a.getString(R.styleable.CommonItemView_left_text);
        int leftIcon = a.getResourceId(R.styleable.CommonItemView_left_icon, 0);
        boolean leftIconVisible = a.getBoolean(R.styleable.CommonItemView_left_icon_visible, false);
        int leftTextColor = a.getColor(R.styleable.CommonItemView_left_text_color, Color.BLACK);
        int rightIcon = a.getResourceId(R.styleable.CommonItemView_right_icon, 0);
        int rightTextColor = a.getColor(R.styleable.CommonItemView_right_text_color, Color.BLACK);
        float leftTextSize = a.getDimensionPixelSize(R.styleable.CommonItemView_left_text_size, 14);
        float rightTextSize = a.getDimensionPixelSize(R.styleable.CommonItemView_right_text_size, 11);
        String rightTextHint = a.getString(R.styleable.CommonItemView_right_text_hint);
        int rightTextHintColor = a.getColor(R.styleable.CommonItemView_right_text_hint_color, Color.LTGRAY);
        String rightText = a.getString(R.styleable.CommonItemView_right_text);
        boolean rightIconVisible = a.getBoolean(R.styleable.CommonItemView_right_icon_visible, false);
        boolean rightTextVisible = a.getBoolean(R.styleable.CommonItemView_right_text_visibility, true);
        int leftLabelStyle = a.getInt(R.styleable.CommonItemView_left_text_style, DEFAULT_TEXT_STYLE);
        isRightSingleLine = a.getBoolean(R.styleable.CommonItemView_right_text_isSingleLine, true);
        a.recycle();
        mTxtName.setText(leftText);
        mTxtName.setTextColor(leftTextColor);
        mTxtName.setTextSize(leftTextSize);
        mTxtName.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        mTxtValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        if (leftIconVisible) {
            mTxtName.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(leftIcon), null, null, null);
        } else {
            mTxtName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        switch (leftLabelStyle) {
            case Typeface.BOLD:
                mTxtName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            default:
                mTxtName.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                break;

        }

        if (isRightSingleLine) {
            mTxtValue.setSingleLine(true);
            mTxtValue.setEllipsize(TextUtils.TruncateAt.END);
        } else {
            mTxtValue.setMaxLines(3);
            mTxtValue.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (rightTextVisible) {
            mTxtValue.setVisibility(VISIBLE);
            mTxtValue.setText(rightText);
            mTxtValue.setTextColor(rightTextColor);
            mTxtValue.setHint(rightTextHint);
            mTxtValue.setHintTextColor(rightTextHintColor);
        } else {
            mTxtValue.setVisibility(GONE);
        }
        if (rightIconVisible) {
            mIvArrow.setImageResource(rightIcon);
        } else {
            mIvArrow.setVisibility(INVISIBLE);
        }


    }

    public void setRightText(String content) {
        if (mTxtValue != null) {
            mTxtValue.setText(content);
        }
    }

    public void setRightText(@StringRes int resId) {
        if (mTxtValue != null) {
            mTxtValue.setText(resId);
        }
    }

    public String getRightText() {
        String result = "";
        if (mTxtValue != null) {
            result = mTxtValue.getText().toString();
            return result;
        }
        return result;
    }

    /**
     * 设置右边文字显示文字
     *
     * @param visibility
     */
    public void setRightTextVisibility(int visibility) {
        mTxtValue.setVisibility(visibility);
    }

    public void setLeftText(String content) {
        if (mTxtName != null) {
            mTxtName.setText(content);
        }
    }

    public void setLeftText(@StringRes int resId) {
        if (mTxtName != null) {
            mTxtName.setText(resId);
        }
    }

    public void setRightIconVisibility(boolean isVisible){
        if(isVisible){
            mIvArrow.setVisibility(View.VISIBLE);
        }else{
            mIvArrow.setVisibility(View.GONE);
        }
    }
}
