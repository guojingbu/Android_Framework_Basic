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
import androidx.fragment.app.FragmentTransaction;

import com.yesway.common_lib.R;
import com.yesway.common_lib.utils.DisplayUtil;

public class CommonDialogVerticalBtnFragment extends DialogFragment {
    public static final String TAG = CommonDialogVerticalBtnFragment.class.getSimpleName();
    private CommonDialogVerticalBtnFragment.OnDialogClickListener mOnDialogClickListener;
    private static boolean isShowing = false;//避免弹多个dialog

    @Override
    public void dismiss() {
        super.dismiss();
        isShowing = false;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
       // super.show(manager, tag);
        isShowing = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss();
    }

    public static boolean isShowing() {
        return isShowing;
    }

    public CommonDialogVerticalBtnFragment setOnDialogClickListener(CommonDialogVerticalBtnFragment.OnDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
        return this;
    }

    public interface OnDialogClickListener {
        void onFirstBtnClick(View view);

        void onSecondBtnClick(View view);

        void onCancelBtnClick(View view);
    }

    ;

    public static CommonDialogVerticalBtnFragment newInstance(CommonDialogVerticalBtnFragment.Builder builder) {
        CommonDialogVerticalBtnFragment commonDialogVerticalBtnFragment = new CommonDialogVerticalBtnFragment();
        Bundle args = new Bundle();
        args.putString("title", builder.title);
        args.putString("describe", builder.describe);
        args.putString("firstBtn", builder.firstBtn);
        args.putString("secondBtn", builder.secondBtn);
        args.putString("cancelBtn", builder.cancelBtn);
        args.putBoolean("isTouchOutSideCancel", builder.isTouchOutSideCancel);
        args.putBoolean("iscancelable", builder.isCancelAble);
        commonDialogVerticalBtnFragment.mOnDialogClickListener = builder.mListener;
        commonDialogVerticalBtnFragment.setArguments(args);
        return commonDialogVerticalBtnFragment;
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
        View view = inflater.inflate(R.layout.common_vertical_btn_fragment_dialog, container, false);
        Bundle arguments = getArguments();
        String title = null;
        String describe = null;
        String firstBtn = null;
        String secondBtn = null;
        String cancelBtn = null;
        boolean isTouchOutSideCancel = true;
        boolean isCancelAble = true;
        if (arguments != null) {
            title = arguments.getString("title");
            describe = arguments.getString("describe");
            firstBtn = arguments.getString("firstBtn");
            secondBtn = arguments.getString("secondBtn");
            cancelBtn = arguments.getString("cancelBtn");
            isTouchOutSideCancel = arguments.getBoolean("isTouchOutSideCancel", true);
            isCancelAble = arguments.getBoolean("iscancelable", true);
        }
        TextView txtTitle = view.findViewById(R.id.txt_common_dialog_title);
        TextView txtDescribe = view.findViewById(R.id.txt_common_dialog_describe);
        Button btnFirst = view.findViewById(R.id.btn_first);
        Button btnSecond = view.findViewById(R.id.btn_second);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        View cancelLine = view.findViewById(R.id.view_cancel_line);
        if (!TextUtils.isEmpty(title)) {
            txtTitle.setText(title);
        } else {
            txtTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(describe)) {
            txtDescribe.setText(describe);
        }
        if (!TextUtils.isEmpty(cancelBtn)) {
            cancelLine.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(cancelBtn);
        } else {
            cancelLine.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(firstBtn)) {
            btnFirst.setText(firstBtn);
        }
        if (!TextUtils.isEmpty(secondBtn)) {
            btnSecond.setText(secondBtn);
        }
        getDialog().setCanceledOnTouchOutside(isTouchOutSideCancel);
        getDialog().setCancelable(isCancelAble);
        onKeyListener(isCancelAble);
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onFirstBtnClick(view);
                }
                dismiss();
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onSecondBtnClick(view);
                }
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDialogClickListener != null) {
                    mOnDialogClickListener.onCancelBtnClick(view);
                }
                dismiss();
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
        String firstBtn;
        String secondBtn;
        String cancelBtn;
        String contentTitle;
        CommonDialogVerticalBtnFragment.OnDialogClickListener mListener;

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

        public Builder setfirstBtn(String firstBtn) {
            this.firstBtn = firstBtn;
            return this;
        }

        public Builder setSecondBtn(String secondBtn) {
            this.secondBtn = secondBtn;
            return this;
        }

        public Builder setCancelBtn(String cancelBtn) {
            this.cancelBtn = cancelBtn;
            return this;
        }

        public Builder setOnDialogClickListener(OnDialogClickListener listener) {
            this.mListener = listener;
            return this;
        }

        public Builder setContentTtile(String contentTitle) {
            this.contentTitle = contentTitle;
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


        public CommonDialogVerticalBtnFragment build() {
            return CommonDialogVerticalBtnFragment.newInstance(this);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        isShowing = false;
    }

}
