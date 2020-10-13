package com.yesway.module_home.view;

import android.Manifest;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yesway.api_lib.utils.ToastUtils;
import com.yesway.common_lib.base.BaseMvpFragment;
import com.yesway.map_lib.location.LocationUtils;
import com.yesway.map_lib.location.OnLocationListener;
import com.yesway.module_home.R;
import com.yesway.module_home.contract.HomeContract;
import com.yesway.module_home.presenter.HomePresenter;
import com.yesway.share_lib.ShareUtils;

import io.reactivex.functions.Consumer;

/**
 * author : guojingbu
 * date   : 2020/9/21
 * desc   :
 */
public class HomeFragment extends BaseMvpFragment<HomeContract.View, HomePresenter> implements HomeContract.View {

    private RxPermissions rxPermissions;

    public final static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(mActivity);
    }

    @Override
    public void initView(View view) {
        rxPermissions = new RxPermissions(this);
        view.findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.getInstance().shareBoardText(mActivity, "氛消夷夏", new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE);
            }
        });

        view.findViewById(R.id.btn_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebPageActivity.startWebPageActivity(mActivity);
            }
        });
        view.findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SDK在Android 6.0下需要进行运行检测的权限如下：
           /*     Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE*/
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LocationUtils.getInstance().startLocation(false, new OnLocationListener() {
                            @Override
                            public void locationSuccess(AMapLocation location) {
                                ToastUtils.showToast("位置信息：" + location.getAddress() + " 经度：" + location.getLatitude() + " 纬度：" + location.getLongitude());
                            }

                            @Override
                            public void locationError() {

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public int bindView() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean enableToolBar() {
        return true;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.title_home;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
