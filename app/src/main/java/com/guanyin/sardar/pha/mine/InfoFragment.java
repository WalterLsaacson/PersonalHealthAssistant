package com.guanyin.sardar.pha.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;


public class InfoFragment extends Fragment {

    EditText tv_pet_name;

    WheelView yearWheelView;
    ArrayList yearList;
    TextView tv_year;

    RadioButton rb_male;
    RadioButton rb_female;

    IndividualInfo mIndividualInfo;

    public static final String EXTRA_INFO_FRAGMENT = "individual_info";

    public static InfoFragment newInstance(IndividualInfo individualInfo) {

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_INFO_FRAGMENT, individualInfo);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // 获取当前需要改变的对象
        mIndividualInfo = (IndividualInfo) getArguments().getSerializable(EXTRA_INFO_FRAGMENT);
        // 设置文字变化前后的问题
        tv_pet_name = (EditText) view.findViewById(R.id.info_pet_name);
        tv_pet_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_pet_name.getTextSize() != 0) {
                    tv_pet_name.selectAll();
                } else {
                    tv_pet_name.setHint("");
                }

            }
        });
        tv_pet_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tv_pet_name.getTextSize() != 0) {
                    mIndividualInfo.setPetName(tv_pet_name.getText().toString());
                }
            }
        });
        // 设置性别选择
        rb_male = (RadioButton) view.findViewById(R.id.male);
        rb_female = (RadioButton) view.findViewById(R.id.female);
        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIndividualInfo.setSex("男");
                }
            }
        });
        rb_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIndividualInfo.setSex("女");
                }
            }
        });


        yearWheelView = (WheelView) view.findViewById(R.id.year_wheel_view);
        yearWheelView.setWheelAdapter(new ArrayWheelAdapter(getActivity()));
        yearWheelView.setWheelSize(3);
        yearWheelView.setSkin(WheelView.Skin.Holo);
        yearList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        final int currentYear = calendar.get(Calendar.YEAR) - 3;
        for (int i = 1980; i <= currentYear; i++) {
            yearList.add(i + "");
        }
        yearWheelView.setWheelData(yearList);
        tv_year = (TextView) view.findViewById(R.id.tv_year);
        yearWheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Object o) {
                String year = yearList.get(position).toString() + "年";
                tv_year.setText(year);
//                mIndividualInfo.setAge(currentYear - 1980 - position + 3 + "");
                mIndividualInfo.setAge(yearList.get(position).toString());
            }
        });
        yearWheelView.setSelection(15);

        return view;
    }
}
