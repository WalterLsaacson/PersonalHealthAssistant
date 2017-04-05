package com.guanyin.sardar.pha.mine.database;


import com.guanyin.sardar.pha.mine.model.IndividualInfo;

import net.sqlcipher.Cursor;
import net.sqlcipher.CursorWrapper;

import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.AGE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BFR;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BLOODFAT;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BLOODPRESSURE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BLOODSUGAR;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.BMI;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.HEIGHT;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.ID;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.PULSE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.SEX;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.TEMPERATURE;
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols.WAISTLINE;
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

        String bmi = getString(getColumnIndex(BMI));
        String bfr = getString(getColumnIndex(BFR));
        String waistLine = getString(getColumnIndex(WAISTLINE));
        String temperature = getString(getColumnIndex(TEMPERATURE));
        String pulse = getString(getColumnIndex(PULSE));
        String bloodPressure = getString(getColumnIndex(BLOODPRESSURE));
        String bloodSugar = getString(getColumnIndex(BLOODSUGAR));
        String bloodFat = getString(getColumnIndex(BLOODFAT));

        // 创建IndividualInfo对象
        IndividualInfo info = new IndividualInfo();

        info.setPetName(name);
        info.setSex(sex);
        info.setAge(age);
        info.setHeight(height);
        info.setWeight(weight);
        info.setId(id);

        info.setBMI(bmi);
        info.setBFR(bfr);
        info.setWaistLine(waistLine);
        info.setTemperature(temperature);
        info.setPulse(pulse);
        info.setBloodPressure(bloodPressure);
        info.setBloodSugar(bloodSugar);
        info.setBloodFat(bloodFat);

        return info;
    }
}
