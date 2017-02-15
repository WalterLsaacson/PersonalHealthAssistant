package com.guanyin.sardar.pha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.guanyin.sardar.pha.alert.AlertFragment;
import com.guanyin.sardar.pha.fragments.FindFragment;
import com.guanyin.sardar.pha.fragments.MineFragment;
import com.guanyin.sardar.pha.fragments.StatusFragment;
import com.guanyin.sardar.pha.utils.Const;

public class FunctionActivity extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener {

    private static final int STATUS_FRAGMENT = 0;
    private static final int FIND_FRAGMENT = 1;
    private static final int MORE_FRAGMENT = 2;
    private static final int MINE_FRAGMENT = 3;

    String TAG = "FunctionActivity";

    BottomNavigationBar mBottomNavigationBar;
    //BottomNavigationItem
    BottomNavigationItem statusItem;
    BottomNavigationItem findItem;
    BottomNavigationItem alertItem;
    BottomNavigationItem mineItem;

    int lastSelectedPosition = STATUS_FRAGMENT;


    StatusFragment mStatusFragment;
    FindFragment mFindFragment;
    AlertFragment mAlertFragment;
    MineFragment mMineFragment;


    // 封装跳转到当前activity的方法
    // 后续可以给intent增加数据
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, FunctionActivity.class);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.guanyin.sardar.pha.R.layout.activity_fuction);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(com.guanyin.sardar.pha.R.id.bottom_navigation_bar);

        if (mBottomNavigationBar != null) {
            mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
            statusItem = new BottomNavigationItem(R.drawable.status, "状态")
                    .setActiveColorResource(R.color.green);
            findItem = new BottomNavigationItem(R.drawable.find, "发现")
                    .setActiveColorResource(R.color.orange);
            alertItem = new BottomNavigationItem(R.drawable.alert, "提醒")
                    .setActiveColorResource(R.color.indigo);
            mineItem = new BottomNavigationItem(R.drawable.mine, "我的")
                    .setActiveColorResource(R.color.midnightblue);
            mBottomNavigationBar
                    .addItem(statusItem).addItem(findItem).addItem(alertItem).addItem(mineItem)
                    .setFirstSelectedPosition(lastSelectedPosition)
                    .initialise();

            mBottomNavigationBar.setTabSelectedListener(this);

            setDefaultFragment();
        } else {
            Const.log(TAG, "mBottomNavigationBar is null!");
        }

    }

    //    添加角标
    //    BadgeItem badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("23")
    //            .setHideOnSelect(true);
    public void addBadgeItem(BadgeItem badgeItem,int whichItem){
        switch (whichItem){
            case 0:
                statusItem.setBadgeItem(badgeItem);
                break;
            case 1:
                findItem.setBadgeItem(badgeItem);
                break;
            case 2:
                alertItem.setBadgeItem(badgeItem);
                break;
            case 3:
                mineItem.setBadgeItem(badgeItem);
                break;
        }
    }
    // 设置默认展示的fragment
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mStatusFragment = StatusFragment.newInstance();
        transaction.replace(com.guanyin.sardar.pha.R.id.fragment_container, mStatusFragment);
        transaction.commit();
        lastSelectedPosition = STATUS_FRAGMENT;
    }

    // 底部控件的点击事件
    @Override
    public void onTabSelected(int position) {
        if (position == lastSelectedPosition)
            return;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case STATUS_FRAGMENT:
                if (mStatusFragment == null) {
                    mStatusFragment = StatusFragment.newInstance();
                }
                transaction.replace(com.guanyin.sardar.pha.R.id.fragment_container, mStatusFragment);
                lastSelectedPosition = STATUS_FRAGMENT;
                break;
            case FIND_FRAGMENT:
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.newInstance();
                }
                transaction.replace(com.guanyin.sardar.pha.R.id.fragment_container, mFindFragment);
                lastSelectedPosition = FIND_FRAGMENT;
                break;
            case MORE_FRAGMENT:
                if (mAlertFragment == null) {
                    mAlertFragment = AlertFragment.newInstance();
                }
                transaction.replace(com.guanyin.sardar.pha.R.id.fragment_container, mAlertFragment);
                lastSelectedPosition = MORE_FRAGMENT;
                break;
            case MINE_FRAGMENT:
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance();
                }
                transaction.replace(com.guanyin.sardar.pha.R.id.fragment_container, mMineFragment);
                lastSelectedPosition = MINE_FRAGMENT;
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {

    }
}
