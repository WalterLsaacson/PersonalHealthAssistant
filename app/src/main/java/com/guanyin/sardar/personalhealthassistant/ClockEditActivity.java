package com.guanyin.sardar.personalhealthassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guanyin.sardar.personalhealthassistant.utils.SingleFragmentActivity;

import static android.R.attr.name;

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
