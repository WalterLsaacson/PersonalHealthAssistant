package com.guanyin.sardar.personalhealthassistant;

import android.support.v4.app.Fragment;

import com.guanyin.sardar.personalhealthassistant.utils.SingleFragmentActivity;

public class EntranceActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FlashFragment.newInstance();
    }


}


