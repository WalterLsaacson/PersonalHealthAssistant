package com.guanyin.sardar.pha.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.guanyin.sardar.pha.entrance.FunctionActivity;
import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndInfoLab;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.utils.Const;

import java.text.NumberFormat;
import java.util.Calendar;

public class EnterInfoActivity extends AppCompatActivity {

    Intent intent;
    TextView tv_forward;
    InfoFragment mInfoFragment;
    BodyInfoFragment mBodyInfoFragment;
    Fragment[] mFragments;
    byte lastIndex = 0;

    IndInfoLab mIndInfoLab;

    static IndividualInfo sIndividualInfo;


    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, EnterInfoActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);

        mIndInfoLab = IndInfoLab.get(EnterInfoActivity.this);
        sIndividualInfo = mIndInfoLab.getIndividualInfo();

        mInfoFragment = InfoFragment.newInstance(sIndividualInfo);
        mBodyInfoFragment = BodyInfoFragment.newInstance(sIndividualInfo);

        mFragments = new Fragment[]{mInfoFragment, mBodyInfoFragment};

        tv_forward = (TextView) findViewById(R.id.tv_forward);

        setFragment();

        tv_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.log("guanyin", sIndividualInfo.toString());
                // 如果有多个fragment可以通过这个按钮进行递进
                if (lastIndex == mFragments.length - 1) {
                    intent = FunctionActivity.newIntent(EnterInfoActivity.this);
                    startActivity(intent);
                    finish();
                    // 将数据录入数据库
                    enterDataBase();
                } else {
                    // 弹出对话框提示信息将不能被再次编辑
                    if (sIndividualInfo.getPetName() == null) {
                        Const.showToast(EnterInfoActivity.this, "请设置昵称");
                    } else {
                        if (sIndividualInfo.getSex() == null) {
                            Const.showToast(EnterInfoActivity.this, "请设置性别");
                        } else {
                            lastIndex++;
                            setFragment();
                        }
                    }
                }
            }
        });
    }

    private void enterDataBase() {
        setBmiAndBfr();
        mIndInfoLab.updateInfo(sIndividualInfo);
    }

    private void setBmiAndBfr() {
        NumberFormat mNumberFormat;
        mNumberFormat = NumberFormat.getNumberInstance();
        mNumberFormat.setMaximumFractionDigits(2);

        int weight = Integer.parseInt(sIndividualInfo.getWeight());
        int height = Integer.parseInt(sIndividualInfo.getHeight());

        String bmi = mNumberFormat.format(weight / (height * height));
        double bmiD = weight / (height * height);
        sIndividualInfo.setBMI(bmi);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int year = Integer.parseInt(sIndividualInfo.getAge());
        String bfr = mNumberFormat.format(
                1.2 * bmiD + 0.23 * (currentYear - year) - 5.4 - 10.8 *
                        (sIndividualInfo.getSex().equals("男") ? 1 : 0));
        sIndividualInfo.setBFR(bfr);
    }


    private void setFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.enter_info_frame, mFragments[lastIndex]);
        fragmentTransaction.commit();
        tv_forward.setText(lastIndex == mFragments.length - 1
                ? "Finish" : "Continue");
    }
}
