package com.yesway.common_lib.widget;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.yesway.common_lib.R;
import com.yesway.common_lib.utils.DisplayUtil;

public class AgreementUpdateDialogFragment extends DialogFragment {
    public static final String TAG = AgreementUpdateDialogFragment.class.getSimpleName();
    private OnDialogClickListener mOnDialogClickListener;
    private static boolean isShowing = false;//避免弹多个dialog

    @Override
    public void dismiss() {
        super.dismiss();
        isShowing = false;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        isShowing = true;
    }

    public static boolean isShowing() {
        return isShowing;
    }

    public AgreementUpdateDialogFragment setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
        return this;
    }

    public interface OnDialogClickListener {
        void onLeftBtnClick(View view);

        void onRightBtnClick(View view);

        void onAgreementClick(View view);
    }

    public static AgreementUpdateDialogFragment newInstance(Builder builder) {
        AgreementUpdateDialogFragment commonDialogFragment = new AgreementUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", builder.title);
        args.putString("describe", builder.describe);
        args.putString("leftbtn", builder.leftbtn);
        args.putString("rightbtn", builder.rightbtn);
        args.putInt("rightbtncolor", builder.btnRightTextColor);
        args.putBoolean("isTouchOutSideCancel", builder.isTouchOutSideCancel);
        args.putBoolean("iscancelable", builder.isCancelAble);
        commonDialogFragment.mOnDialogClickListener = builder.mListener;
        commonDialogFragment.setArguments(args);
        return commonDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(getResources().getDisplayMetrics().widthPixels - DisplayUtil.dpToPx(40) * 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agreement_update_dialog, container, false);
        Bundle arguments = getArguments();
        String title = null;
        String describe = null;
        String leftbtn = null;
        String rightbtn = null;
        int rightBtnTextColor = 0;
        boolean isTouchOutSideCancel = true;
        boolean isCancelAble = true;
        if (arguments != null) {
            title = arguments.getString("title");
            describe = arguments.getString("describe");
            leftbtn = arguments.getString("leftbtn");
            rightbtn = arguments.getString("rightbtn");
            rightBtnTextColor = arguments.getInt("rightbtncolor", 0);
            isTouchOutSideCancel = arguments.getBoolean("isTouchOutSideCancel", true);
            isCancelAble = arguments.getBoolean("iscancelable", true);
        }
        TextView txtTitle = view.findViewById(R.id.txt_common_dialog_title);
        TextView txtDescribe = view.findViewById(R.id.txt_common_dialog_describe);
        Button btnLeft = view.findViewById(R.id.btn_common_dialog_left);
        Button btnRight = view.findViewById(R.id.btn_common_dialog_right);
        View btnHalving = view.findViewById(R.id.view_halving_line);
        TextView tvAgreement = view.findViewById(R.id.tv_agreement);
        if (!TextUtils.isEmpty(title)) {
            txtTitle.setText(title);
        } else {
            txtTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(describe)) {
            txtDescribe.setText(describe);
        }
        if (!TextUtils.isEmpty(leftbtn)) {
            btnHalving.setVisibility(View.VISIBLE);
            btnLeft.setVisibility(View.VISIBLE);
            btnLeft.setText(leftbtn);
        } else {
            btnHalving.setVisibility(View.GONE);
            btnLeft.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(rightbtn)) {
            btnRight.setText(rightbtn);
        }
        if (rightBtnTextColor != 0) {
            btnRight.setTextColor(rightBtnTextColor);
        }
        getDialog().setCanceledOnTouchOutside(isTouchOutSideCancel);
        getDialog().setCancelable(isCancelAble);
        onKeyListener(isCancelAble);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onLeftBtnClick(view);
                }
                dismiss();
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onRightBtnClick(view);
                }
            }
        });
        tvAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onAgreementClick(v);
                }
            }
        });
        return view;
    }

    /**
     * 这里主要是处理返回键逻辑
     */
    private void onKeyListener(final boolean isCancelAble) {
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    switch (keyCode) {
                        // 返回键
                        case KeyEvent.KEYCODE_BACK:
                            if (!isCancelAble) {
                                return true;
                            }
                        default:
                            break;
                    }
                    return false;
                }
            });
        }
    }

    public static class Builder {
        boolean isCancelAble;
        boolean isTouchOutSideCancel;
        String title;
        String describe;
        String leftbtn;
        String rightbtn;
        int btnRightTextColor;
        OnDialogClickListener mListener;

        public Builder() {
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescribe(String describe) {
            this.describe = describe;
            return this;
        }

        public Builder setLeftbtn(String leftbtn) {
            this.leftbtn = leftbtn;
            return this;
        }

        public Builder setRightbtn(String rightbtn) {
            this.rightbtn = rightbtn;
            return this;
        }

        public Builder setOnDialogClickListener(OnDialogClickListener listener) {
            this.mListener = listener;
            return this;
        }

        public Builder setRightbtnTextColor(int rightBtnTextcolor) {
            this.btnRightTextColor = rightBtnTextcolor;
            return this;
        }

        public Builder setTouchOutSideCancel(boolean touchOutSideCancel) {
            isTouchOutSideCancel = touchOutSideCancel;
            return this;
        }

        public Builder setCancelAble(boolean cancelAble) {
            isCancelAble = cancelAble;
            return this;
        }

        public AgreementUpdateDialogFragment build() {
            return AgreementUpdateDialogFragment.newInstance(this);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        isShowing = false;
    }
}
