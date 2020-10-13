package com.yesway.common_lib.utils;

import android.Manifest;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yesway.api_lib.utils.ToastUtils;
import com.yesway.common_lib.R;

import io.reactivex.functions.Consumer;

/**
 * 必要权限检查
 */
public class PermissionCheckUtil {
    public static void check(final FragmentActivity activity) {
        new RxPermissions(activity).request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    ToastUtils.showToast(activity.getString(R.string.must_permission_content));
                }
            }
        });
    }
}
