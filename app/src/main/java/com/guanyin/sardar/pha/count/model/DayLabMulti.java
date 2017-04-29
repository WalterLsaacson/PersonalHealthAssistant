package com.guanyin.sardar.pha.count.model;


import android.content.ContentValues;
import android.content.Context;

import com.guanyin.sardar.pha.status.database.DayCipherHelper;
import com.guanyin.sardar.pha.status.database.DayCursorWrapper;
import com.guanyin.sardar.pha.status.database.DayDbSchema;
import com.guanyin.sardar.pha.status.model.Day;
import com.guanyin.sardar.pha.utils.Const;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.DATE;

public class DayLabMulti {

    private static DayLabMulti sDayLabMulti;
    private SQLiteDatabase mSQLiteDatabase;

    private DayLabMulti(Context context) {
        Context context1 = context.getApplicationContext();
        mSQLiteDatabase = new DayCipherHelper(context1).getWritableDatabase("guanyin");
    }

    public static DayLabMulti get(Context context) {
        if (sDayLabMulti == null) {
            sDayLabMulti = new DayLabMulti(context);
        }
        return sDayLabMulti;
    }

    // 从游标拿到数据库内容并转换为Day对象
    public ArrayList<Day> getDays() {
        DayCursorWrapper dayCursorWrapper = queryInfo(null,
                null);
        ArrayList<Day> days = new ArrayList<>();
        dayCursorWrapper.moveToFirst();
        while (!dayCursorWrapper.isAfterLast()) {
            days.add(dayCursorWrapper.getDay());
            dayCursorWrapper.moveToNext();
        }
        dayCursorWrapper.close();
        return days;
    }

    // 从游标拿到数据库内容并转换为Day对象
    public ArrayList<Day> getSomeDays(long startTime, long endTime) {
//        mSQLiteDatabase.execSQL("select * from " + DayDbSchema.DayTable.NAME + "" +
//                " where " + DayDbSchema.DayTable.Cols.DATE + " >= " + startTime +
//                " AND " + DayDbSchema.DayTable.Cols.DATE + " <= " + endTime);
        //, " <= " + endTime}
        // 首先得到需要查询的范围
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        String start = Const.getCurrentDateInteger(calendar) + "";
        calendar.setTimeInMillis(endTime);
        String end = Const.getCurrentDateInteger(calendar) + "";
        // 执行查询语句得到游标
        String sql = "select * from everyday where date >=? and date <=? order by date";
        Cursor cursor = mSQLiteDatabase.rawQuery(sql, new String[]{start, end});
        // 从游标拿到数值并返回
        ArrayList<Day> days = new ArrayList<>();
        DayCursorWrapper dayCursorWrapper = new DayCursorWrapper(cursor);
        dayCursorWrapper.moveToFirst();
        while (!dayCursorWrapper.isAfterLast()) {
            days.add(dayCursorWrapper.getDay());
            dayCursorWrapper.moveToNext();
        }
        dayCursorWrapper.close();
        return days;
    }


    // 从数据库中拿到游标
    //  Cursor cursor = db.query(SqliteHelper. TB_NAME, null,
    // UserInfo.USERID+ "=?", new String[]{UserId}, null, null, null );
    private DayCursorWrapper queryInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(DayDbSchema.DayTable.NAME, null,
                whereClause,
                whereArgs,
                null, null, null);
        return new DayCursorWrapper(cursor);
    }

}
