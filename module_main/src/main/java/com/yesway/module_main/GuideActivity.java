package com.yesway.module_main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

import com.yesway.common_lib.base.BaseActivity;
import com.yesway.module_main.adapter.GuideAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;


public class GuideActivity extends BaseActivity {
    private GuideAdapter mGuideAdapter;
    private Button mBtnMainEnter;
    private ViewPager mViewPager;
    private MagicIndicator magicIndicator;

    public static void startGuideActivity(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.pager_main_guide);
        mBtnMainEnter = findViewById(R.id.img_main_enter);
        mBtnMainEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startLoginActivity(GuideActivity.this);
                finish();
            }
        });
        mGuideAdapter = new GuideAdapter(this);
        mViewPager.setAdapter(mGuideAdapter);
        initMagicIndicator();

    }

    @Override
    public int bindView() {
        return R.layout.activity_guide;
    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    protected boolean fitsSystemWindows() {
        return false;
    }

    private void initMagicIndicator() {
        magicIndicator = (MagicIndicator) findViewById(R.id.indicator);
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(mGuideAdapter.getCount());
        circleNavigator.setCircleColor(getResources().getColor(R.color.green_1));
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
                if (position == mGuideAdapter.getCount() - 1) {
                    mBtnMainEnter.setVisibility(View.VISIBLE);
                } else {
                    mBtnMainEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
