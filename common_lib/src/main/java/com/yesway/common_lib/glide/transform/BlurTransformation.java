package com.yesway.common_lib.glide.transform;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


/**
 * 高斯模糊
 */
@RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
public class BlurTransformation extends BitmapTransformation {

    private static final String ID = BlurTransformation.class.getName();
    private static final byte[] ID_BYTES = ID.getBytes(Key.CHARSET);
    public static final int DEFAULT_RADIUS = 15;
    private int radius = DEFAULT_RADIUS;

    public BlurTransformation(@IntRange(from = 0) int radius) {
        this.radius = radius;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);

    }
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurFastHelper.doBlur(toTransform, radius, true);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BlurTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
