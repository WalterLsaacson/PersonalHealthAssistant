package com.guanyin.sardar.pha.mine.model;


import android.content.ContentValues;
import android.content.Context;

import com.guanyin.sardar.pha.mine.database.*;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class IndInfoLab {

    private static IndInfoLab sIndInfoLab;

    private SQLiteDatabase mSQLiteDatabase;

    private IndInfoLab(Context context) {
        Context context1 = context.getApplicationContext();
        mSQLiteDatabase = new InfoCipherHelper(context1).getWritableDatabase("guanyin");
    }

    public static IndInfoLab get(Context context) {
        if (sIndInfoLab == null) {
            sIndInfoLab = new IndInfoLab(context);
        }
        return sIndInfoLab;
    }

    // 从游标拿到数据库内容并转换为IndividualInfo对象
    public IndividualInfo getIndividualInfo() {
        InfoCursorWrapper cursorWrapper = queryInfo(null, null);
        cursorWrapper.moveToFirst();
        IndividualInfo sIndividualInfo = cursorWrapper.getInfo();
        cursorWrapper.close();
        return sIndividualInfo;
    }


    // 从数据库中拿到游标
    private InfoCursorWrapper queryInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(InfoDbSchema.InfoTable.NAME, null, whereClause,
                whereArgs,
                null, null, null);
        return new InfoCursorWrapper(cursor);
    }

    public void updateInfo(IndividualInfo individualInfo) {
        String idString = individualInfo.getId();
        ContentValues values = getContentValues(individualInfo);

        mSQLiteDatabase.update(InfoDbSchema.InfoTable.NAME, values, InfoDbSchema.InfoTable.Cols
                .ID + " = ?", new
                String[]{idString});
    }

    // 从clock对象转换到数据库可以识别的contentValues键值对对象
    private static ContentValues getContentValues(IndividualInfo individualInfo) {
        ContentValues values = new ContentValues();
        values.put(InfoDbSchema.InfoTable.Cols.NAME, individualInfo.getPetName());
        values.put(InfoDbSchema.InfoTable.Cols.AGE, individualInfo.getAge());
        values.put(InfoDbSchema.InfoTable.Cols.SEX, individualInfo.getSex());
        values.put(InfoDbSchema.InfoTable.Cols.HEIGHT, individualInfo.getHeight());
        values.put(InfoDbSchema.InfoTable.Cols.WEIGHT, individualInfo.getWeight());
        values.put(InfoDbSchema.InfoTable.Cols.WAISTLINE, individualInfo.getWaistLine());
        values.put(InfoDbSchema.InfoTable.Cols.PULSE, individualInfo.getPulse());
        values.put(InfoDbSchema.InfoTable.Cols.SYSTOLICRESSURE, individualInfo
                .getSystolicPressure());
        values.put(InfoDbSchema.InfoTable.Cols.DIASTOLICPRESSURE, individualInfo
                .getDiastolicPressure());
        values.put(InfoDbSchema.InfoTable.Cols.BLOODSUGAR, individualInfo.getBloodSugar());
        values.put(InfoDbSchema.InfoTable.Cols.TC, individualInfo.getTC());
        values.put(InfoDbSchema.InfoTable.Cols.WATERINTAKE, individualInfo.getWaterIntake());
        values.put(InfoDbSchema.InfoTable.Cols.SLEEPTIME, individualInfo.getSleepTime());
        values.put(InfoDbSchema.InfoTable.Cols.RUNDURATION, individualInfo.getRunDuration());
        values.put(InfoDbSchema.InfoTable.Cols.HEALTHMARK, individualInfo.getHealthMark());
        return values;
    }
}
