package com.guanyin.sardar.pha.status.database;


import com.guanyin.sardar.pha.status.model.Day;
import com.guanyin.sardar.pha.utils.Const;

import net.sqlcipher.Cursor;
import net.sqlcipher.CursorWrapper;

import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.DATE;
import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.SLEEP;
import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.SPORT;
import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.WATER;

// 这个类存在的意义是封装转换的方法
public class DayCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DayCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Day getDay() {
        // 从数据库对象到Day对象的转换
        int date = getInt(getColumnIndex(DATE));
        String sleep = getString(getColumnIndex(SLEEP));
        String sport = getString(getColumnIndex(SPORT));
        String water = getString(getColumnIndex(WATER));

        // Day对象
        Day day = new Day();

        day.setDate(date);
        day.setSleep(Const.stringToInteger(sleep));
        day.setStep(Const.stringToInteger(sport));
        day.setWater(Const.stringToInteger(water));

        return day;
    }
}
