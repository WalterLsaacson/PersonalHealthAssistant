package com.guanyin.sardar.pha.mine.database;


import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.utils.Const;

import net.sqlcipher.Cursor;
import net.sqlcipher.CursorWrapper;

import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.AGE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BLOODSUGAR;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.DIASTOLICPRESSURE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.HEALTHMARK;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.HEIGHT;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.ID;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.PULSE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.RUNDURATION;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.SEX;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.SLEEPTIME;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.SYSTOLICRESSURE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.TC;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.WAISTLINE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.WATERINTAKE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.WEIGHT;

// 这个类存在的意义是封装转换的方法
public class InfoCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public InfoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public IndividualInfo getInfo() {
        // 从数据库对象到IndividualInfo对象的转换
        String name = getString(getColumnIndex(InfoDbSchema.InfoTable.Cols.NAME));
        String sex = getString(getColumnIndex(SEX));
        String age = getString(getColumnIndex(AGE));
        String height = getString(getColumnIndex(HEIGHT));
        String weight = getString(getColumnIndex(WEIGHT));
        String id = getString(getColumnIndex(ID));

        String waistLine = getString(getColumnIndex(WAISTLINE));
        String pulse = getString(getColumnIndex(PULSE));
        String systolicPressure = getString(getColumnIndex(SYSTOLICRESSURE));
        String diastolicPressure = getString(getColumnIndex(DIASTOLICPRESSURE));
        String bloodSugar = getString(getColumnIndex(BLOODSUGAR));
        String tc = getString(getColumnIndex(TC));

        String waterIntake = getString(getColumnIndex(WATERINTAKE));
        String sleepTime = getString(getColumnIndex(SLEEPTIME));
        String runDuration = getString(getColumnIndex(RUNDURATION));
        String healthMark = getString(getColumnIndex(HEALTHMARK));

        // 创建IndividualInfo对象
        IndividualInfo info = new IndividualInfo();

        info.setPetName(name);
        info.setSex(sex);
        info.setAge(age);
        info.setHeight(height);
        info.setWeight(weight);
        info.setId(id);

        info.setWaistLine(waistLine);
        info.setPulse(pulse);
        info.setSystolicPressure(systolicPressure);
        info.setDiastolicPressure(diastolicPressure);
        info.setBloodSugar(bloodSugar);
        info.setTC(tc);

        info.setWaterIntake(Const.stringToInteger(waterIntake));
        info.setSleepTime(Const.stringToInteger(sleepTime));
        info.setRunDuration(Const.stringToInteger(runDuration));
        info.setHealthMark(Const.stringToInteger(healthMark));

        return info;
    }
}
