package com.yesway.common_lib.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yesway.common_lib.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 照片选择框
 * guojingbu
 */
public class PhotoSelectDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = PhotoSelectDialog.class.getSimpleName();
    private OnPhotoClickLisener mOnClickLisener;
    private List<String> mPhotoPaths = new ArrayList<>();

    private boolean bigPictureVisible = false;
    private OnClickBigPictureListener mOnClickBigPictureListener;


    public void setOnClickLisener(OnPhotoClickLisener onPhotoClickLisener) {
        mOnClickLisener = onPhotoClickLisener;
    }

    public void setOnClickBigPictureListener(OnClickBigPictureListener onClickBigPictureListener){
        mOnClickBigPictureListener = onClickBigPictureListener;
    }

    public static PhotoSelectDialog newInstance() {
        return new PhotoSelectDialog();
    }

    public interface OnPhotoClickLisener {
        void onSelectPhotoClick(List<String> list);
    }
    public interface OnClickBigPictureListener {
        void onBigPictureClick();
    }
    public void setBigPictureVisible(boolean isVisible){
        bigPictureVisible = isVisible;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        getDialog().getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_select, container, false);
        Button btnSelectPhoto = (Button) view.findViewById(R.id.btn_common_select_photo);
        Button btnTakephoto = (Button) view.findViewById(R.id.btn_common_take_photo);
        Button btnCheckPicture = (Button) view.findViewById(R.id.btn_big_picture);
        View mViewDivider = (View) view.findViewById(R.id.view_second_divider);
        Button btnCancel = (Button) view.findViewById(R.id.btn_common_photo_cancel);

        btnSelectPhoto.setOnClickListener(this);
        btnTakephoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if(bigPictureVisible){
            btnCheckPicture.setVisibility(View.VISIBLE);
            mViewDivider.setVisibility(View.VISIBLE);
            btnCheckPicture.setOnClickListener(this);
        }else{
            btnCheckPicture.setVisibility(View.GONE);
            mViewDivider.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_common_take_photo) {
            takePhoto();
        } else if (i == R.id.btn_common_select_photo) {
            selectPhoto();
        }else if (i == R.id.btn_big_picture) {
            if(mOnClickBigPictureListener!=null){
                mOnClickBigPictureListener.onBigPictureClick();
            }
            dismiss();
        } else if (i == R.id.btn_common_photo_cancel) {
            dismiss();
        }
    }

    private void selectPhoto() {
        // 单独拍照
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isCamera(false)// 是否显示拍照按钮
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void takePhoto() {
        // 单独拍照
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .isCamera(false)// 是否显示拍照按钮
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (mOnClickLisener != null) {
                        if (selectList != null && !selectList.isEmpty()) {
                            for (LocalMedia localMedia : selectList) {
                                mPhotoPaths.add(localMedia.getCompressPath());
                            }
                        }
                        mOnClickLisener.onSelectPhotoClick(mPhotoPaths);
                    }
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }
}
