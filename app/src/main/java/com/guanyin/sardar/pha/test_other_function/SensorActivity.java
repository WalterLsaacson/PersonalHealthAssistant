package com.guanyin.sardar.pha.test_other_function;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.utils.Const;


public class SensorActivity extends Activity implements SensorEventListener {

    RadioButton roughBtn;
    RadioButton meticulousBtn;
    TextView mTextViewLight;

    SensorManager mSensorManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化控件
        setContentView(R.layout.sensor_activity);
        roughBtn = (RadioButton) findViewById(R.id.rough);
        meticulousBtn = (RadioButton) findViewById(R.id.meticulous);
        mTextViewLight = (TextView) findViewById(R.id.lightRank);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    // 等级函数
    private void rough(float value) {
        if (value <= 3000) {
            mTextViewLight.setText("暗");
        } else if (value <= 6000) {
            mTextViewLight.setText("普通");
        } else if (value <= 10000) {
            mTextViewLight.setText("亮");
        } else if (value <= 30000) {
            mTextViewLight.setText("很亮");
        } else {
            mTextViewLight.setText("巨亮");
        }
        Const.log("SensorActivity", "current light sensor is" + value);
    }

    private void meticulous(float value) {
        // 同上
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        if (roughBtn.isChecked()) {
            rough(value);
        } else {
            meticulous(value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
