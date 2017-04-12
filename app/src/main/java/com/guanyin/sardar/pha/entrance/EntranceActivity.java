package com.guanyin.sardar.pha.entrance;

import android.support.v4.app.Fragment;

import com.guanyin.sardar.pha.utils.SingleFragmentActivity;

public class EntranceActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FlashFragment.newInstance();
    }


}


