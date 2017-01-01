package com.guanyin.sardar.personalhealthassistant.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guanyin.sardar.personalhealthassistant.database.ClockBaseHelper;
import com.guanyin.sardar.personalhealthassistant.database.ClockCursorWrapper;
import com.guanyin.sardar.personalhealthassistant.database.ClockDbSchema.CLockTable;
import com.guanyin.sardar.personalhealthassistant.database.ClockDbSchema.CLockTable.Cols;

import java.util.ArrayList;
import java.util.List;

public class ClockLab {

    private static ClockLab sClockLab;

    // 声明数据库字段
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public static ClockLab get(Context context) {
        if (sClockLab == null) {
            sClockLab = new ClockLab(context);
        }
        return sClockLab;
    }

    private ClockLab(Context context) {
        mContext = context.getApplicationContext();
        // 这一句调用CrimeBaseHelper用于创建一个新的数据库或者返回已经存在的数据库
        mSQLiteDatabase = new ClockBaseHelper(mContext).getWritableDatabase();
    }

    // 获取提醒列表
    public List<Clock> getClocks() {
        List<Clock> clocks = new ArrayList<>();
        ClockCursorWrapper cursorWrapper = queryClocks(null, null);

        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            clocks.add(cursorWrapper.getClock());
            cursorWrapper.moveToNext();
        }

        cursorWrapper.close();

        return clocks;
    }

    private ClockCursorWrapper queryClocks(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(CLockTable.NAME, null, whereClause, whereArgs,
                null, null, null);
        return new ClockCursorWrapper(cursor);
    }

    // 添加clock到列表
//    public void addClock(Clock clock) {
//        ContentValues values = getContentValues(clock);
//
//        mSQLiteDatabase.insert(CLockTable.NAME, null, values);
//    }

    // 更新修改过的clock数据
    public void updateClock(Clock clock) {
        String idString = clock.getId();
        ContentValues values = getContentValues(clock);

        mSQLiteDatabase.update(CLockTable.NAME, values, Cols.ID + " = ?", new
                String[]{idString});
    }

    // 从clock对象转换到数据库可以识别的contentValues键值对对象
    private static ContentValues getContentValues(Clock clock) {
        ContentValues values = new ContentValues();
        values.put(Cols.TITLE, clock.getTitle());
        values.put(Cols.DATE, clock.getDate());
        values.put(Cols.OPENED, clock.isOpen() ? 1 : 0);
        values.put(Cols.MUSIC, clock.getMusic());
        return values;
    }
}
