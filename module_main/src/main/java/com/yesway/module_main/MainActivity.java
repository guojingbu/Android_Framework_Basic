package com.yesway.module_main;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yesway.common_lib.base.BaseActivity;
import com.yesway.common_lib.manager.JpushManager;
import com.yesway.common_lib.router.IHomeProvider;
import com.yesway.common_lib.router.IMeProvider;
import com.yesway.common_lib.router.IVehicleProvider;
import com.yesway.common_lib.router.RouterPath;
import com.yesway.common_lib.widget.bottomnavigation.BottomNavigationViewEx;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity {

    private BottomNavigationViewEx mBottomNavi;
    private Fragment mHomeFragment;
    private Fragment mVehicleFragment;
    private Fragment mMeFragment;

    @Autowired(name = RouterPath.HOME_PROVIDER_PATH)
    IHomeProvider mHomeProvider;
    @Autowired(name = RouterPath.VEHICLE_PROVIDER_PATH)
    IVehicleProvider mVehicleProvider;
    @Autowired(name = RouterPath.ME_PROVIDER_PATH)
    IMeProvider mMeProvider;

    private Fragment mCurrentFragment;

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData() {
        JpushManager.setAlias(this,"13649214250");
    }

    @Override
    public void initView() {
        mBottomNavi = findViewById(R.id.bottom_navi);
        Badge badge = addBadgeAt(1, 0);
        badge.setBadgeNumber(100);
        mBottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.navi_home) {
                    if (mCurrentFragment == mHomeFragment) return false;
                    switchFragment(mCurrentFragment, mHomeFragment, "home_fragment");
                    mCurrentFragment = mHomeFragment;
                    return true;

                } else if (itemId == R.id.navi_vehicle) {
                    if (mCurrentFragment == mVehicleFragment) return false;
                    switchFragment(mCurrentFragment, mVehicleFragment, "vehicle_fragment");
                    mCurrentFragment = mVehicleFragment;
                    return true;

                } else if (itemId == R.id.navi_me) {
                    if (mCurrentFragment == mMeFragment) return false;
                    switchFragment(mCurrentFragment, mMeFragment, "me_fragment");
                    mCurrentFragment = mMeFragment;
                    return true;
                }
                return false;
            }
        });
        if (mHomeProvider != null) {
            mHomeFragment = mHomeProvider.getMainHomeFragment();
        }
        if (mVehicleProvider != null) {
            mVehicleFragment = mVehicleProvider.getMainVehicleFragment();
        }
        if (mMeProvider != null) {
            mMeFragment = mMeProvider.getMainMeFragment();
        }
        mCurrentFragment = mHomeFragment;
        if (mHomeFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mHomeFragment, "home_fragment").commit();
        }
    }

    @Override
    public int bindView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(25, 2, true)
                .bindTarget(mBottomNavi.getBottomNavigationItemView(position));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JpushManager.delAlias(this);
    }

    /**
     * 切换fragment
     *
     * @param from
     * @param to
     * @param tag
     */
    private void switchFragment(Fragment from, Fragment to, String tag) {
        if (from == null || to == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.container, to, tag).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }
}
