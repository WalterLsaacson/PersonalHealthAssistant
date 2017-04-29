package com.guanyin.sardar.pha.status.model;


import android.content.ContentValues;
import android.content.Context;

import com.guanyin.sardar.pha.status.database.DayCipherHelper;
import com.guanyin.sardar.pha.status.database.DayCursorWrapper;
import com.guanyin.sardar.pha.status.database.DayDbSchema;
import com.guanyin.sardar.pha.utils.Const;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols.DATE;

public class DayLab {

    private static DayLab sDayLab;

    private SQLiteDatabase mSQLiteDatabase;

    private DayLab(Context context) {
        Context context1 = context.getApplicationContext();
        mSQLiteDatabase = new DayCipherHelper(context1).getWritableDatabase("guanyin");
    }

    public static DayLab get(Context context) {
        if (sDayLab == null) {
            sDayLab = new DayLab(context);
        }
        return sDayLab;
    }

    // 从游标拿到数据库内容并转换为Day对象
    public Day getDay() {
        int todayDate = Const.getCurrentDateInteger();
        DayCursorWrapper dayCursorWrapper = queryInfo(DayDbSchema.DayTable.Cols.DATE,
                new String[]{todayDate + ""});
        Day mDay;
        if (dayCursorWrapper.moveToFirst()) {
            mDay = dayCursorWrapper.getDay();
            dayCursorWrapper.close();

        } else {
            mDay = new Day(todayDate);
            addDay(mDay);
            DayCursorWrapper dayCursorWrapperE = queryInfo(DayDbSchema.DayTable.Cols.DATE,
                    new String[]{todayDate + ""});
            dayCursorWrapperE.moveToFirst();
            mDay = dayCursorWrapperE.getDay();
            dayCursorWrapperE.close();
        }
        Const.log("DayLab", mDay.toString());

        return mDay;
    }


    // 从数据库中拿到游标
    //  Cursor cursor = db.query(SqliteHelper. TB_NAME, null,
    // UserInfo.USERID+ "=?", new String[]{UserId}, null, null, null );
    private DayCursorWrapper queryInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(DayDbSchema.DayTable.NAME, null,
                whereClause + "=?",
                whereArgs,
                null, null, null);
        return new DayCursorWrapper(cursor);
    }

    private void addDay(Day day) {
        ContentValues values = getContentValues(day);
        mSQLiteDatabase.insert(DayDbSchema.DayTable.NAME, DayDbSchema.DayTable.Cols.DATE,
                values);
    }

    public void updateDay(Day day) {
        String idString = day.getDate() + "";
        ContentValues values = getContentValues(day);

        mSQLiteDatabase.update(DayDbSchema.DayTable.NAME, values, DATE
                + " = ?", new
                String[]{idString});
    }


    // 从clock对象转换到数据库可以识别的contentValues键值对对象
    private static ContentValues getContentValues(Day day) {
        ContentValues values = new ContentValues();
        values.put(DATE, day.getDate());
        values.put(DayDbSchema.DayTable.Cols.SLEEP, day.getSleep());
        values.put(DayDbSchema.DayTable.Cols.SPORT, day.getStep());
        values.put(DayDbSchema.DayTable.Cols.WATER, day.getWater());
        return values;
    }

}
