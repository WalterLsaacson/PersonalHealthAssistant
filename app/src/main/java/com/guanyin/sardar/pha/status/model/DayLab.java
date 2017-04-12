package com.guanyin.sardar.pha.status.model;


import android.content.ContentValues;
import android.content.Context;
import android.net.UrlQuerySanitizer;

import com.guanyin.sardar.pha.mine.database.InfoCipherHelper;
import com.guanyin.sardar.pha.mine.database.InfoCursorWrapper;
import com.guanyin.sardar.pha.mine.database.InfoDbSchema;
import com.guanyin.sardar.pha.mine.model.IndividualInfo;
import com.guanyin.sardar.pha.status.database.DayCipherHelper;
import com.guanyin.sardar.pha.status.database.DayCursorWrapper;
import com.guanyin.sardar.pha.status.database.DayDbSchema;

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
        DayCursorWrapper dayCursorWrapper = queryInfo(null, null);
        dayCursorWrapper.moveToFirst();
        Day mDay = dayCursorWrapper.getDay();
        dayCursorWrapper.close();
        return mDay;
    }


    // 从数据库中拿到游标
    private DayCursorWrapper queryInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(DayDbSchema.DayTable.NAME, null, whereClause,
                whereArgs,
                null, null, null);
        return new DayCursorWrapper(cursor);
    }

    public void updateDay(Day day) {
        String idString = day.getDate();
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
        values.put(DayDbSchema.DayTable.Cols.SPORT, day.getSport());
        values.put(DayDbSchema.DayTable.Cols.WATER, day.getWater());
        return values;
    }
}
