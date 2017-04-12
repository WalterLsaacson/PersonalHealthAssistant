package com.guanyin.sardar.pha.alert;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.guanyin.sardar.pha.utils.SingleFragmentActivity;

public class ClockEditActivity extends SingleFragmentActivity {

    public static final String EXTRA_CLOCK_EDIT_ID = "com.guanyin.sardar.pha.clock_name";

    @Override
    protected Fragment createFragment() {
        return EditClockFragment.newInstance(getIntent().getStringExtra(EXTRA_CLOCK_EDIT_ID));
    }

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, ClockEditActivity.class);
        intent.putExtra(EXTRA_CLOCK_EDIT_ID, id);
        return intent;
    }

}
