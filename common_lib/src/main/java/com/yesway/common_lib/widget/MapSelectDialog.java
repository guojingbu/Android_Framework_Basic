package com.yesway.common_lib.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yesway.common_lib.R;
import com.yesway.common_lib.utils.OpenLocalMapUtil;

public class MapSelectDialog extends Dialog implements View.OnClickListener {


    private Activity mContext;
    private View contentView;
    private final TextView mTvAmap;
    private final TextView mTvBaidu;
    private final TextView mTvGoogle;

    public MapSelectDialog(Activity context) {
        super(context, R.style.BottomDialogStyle);
        this.mContext = context;

        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_map_select, null);
        mTvAmap = (TextView) contentView.findViewById(R.id.tv_amap);
        mTvBaidu = (TextView) contentView.findViewById(R.id.tv_baidu);
        mTvGoogle = (TextView) contentView.findViewById(R.id.tv_google);
        mTvBaidu.setOnClickListener(this);
        mTvAmap.setOnClickListener(this);
        mTvGoogle.setOnClickListener(this);
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(this);

        mTvAmap.setVisibility(OpenLocalMapUtil.isGdMapInstalled() ? View.VISIBLE : View.GONE);
        mTvBaidu.setVisibility(OpenLocalMapUtil.isBaiduMapInstalled() ? View.VISIBLE : View.GONE);
        mTvGoogle.setVisibility(OpenLocalMapUtil.isGoogleMapInstalled() ? View.VISIBLE : View.GONE);

        this.setContentView(contentView);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
        this.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.getWindow().setGravity(Gravity.BOTTOM);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_amap) {
            if (mOnOpenMapListener != null) {
                mOnOpenMapListener.startAMap();
            }
            cancel();
        } else if (id == R.id.tv_baidu) {
            if (mOnOpenMapListener != null) {
                mOnOpenMapListener.startBaiduMap();
            }
            cancel();
        } else if (id == R.id.tv_google) {
            if (mOnOpenMapListener != null) {
                mOnOpenMapListener.startGoogleMap();
            }
            cancel();
        } else if (id == R.id.tv_cancel) {
            cancel();
        }

    }

    public interface OnOpenMapListener {

        void startBaiduMap();

        void startAMap();

        void startGoogleMap();
    }

    public OnOpenMapListener mOnOpenMapListener;

    public void setOnOpenMapListener(OnOpenMapListener onOpenMapListener) {
        this.mOnOpenMapListener = onOpenMapListener;
    }
}
