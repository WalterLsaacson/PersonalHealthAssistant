package com.guanyin.sardar.pha.mine.model;


import android.content.ContentValues;
import android.content.Context;

import com.guanyin.sardar.pha.mine.database.*;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class IndInfoLab {

    private static IndInfoLab sIndInfoLab;

    // 声明数据库字段
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private IndInfoLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new InfoCipherHelper(mContext).getWritableDatabase("guanyin");
    }

    public static IndInfoLab get(Context context) {
        if (sIndInfoLab == null) {
            sIndInfoLab = new IndInfoLab(context);
        }
        return sIndInfoLab;
    }

    // 从游标拿到数据库内容并转换为IndividualInfo对象
    public  IndividualInfo getIndividualInfo() {
        InfoCursorWrapper cursorWrapper = queryInfo(null, null);
        cursorWrapper.moveToFirst();
        IndividualInfo mIndividualInfo = cursorWrapper.getInfo();
        cursorWrapper.close();
        return mIndividualInfo;
    }


    // 从数据库中拿到游标
    private InfoCursorWrapper queryInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query("info", null, whereClause, whereArgs,
                null, null, null);
        return new InfoCursorWrapper(cursor);
    }

    public void updateInfo(IndividualInfo individualInfo) {
        String idString = individualInfo.getId();
        ContentValues values = getContentValues(individualInfo);

        // TODO: 2017/4/2 多人使用的场景
        mSQLiteDatabase.update(InfoDbSchema.InfoTable.NAME,values,InfoDbSchema.InfoTable.Cols.ID+ " = ?", new
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
        values.put(InfoDbSchema.InfoTable.Cols.BMI, individualInfo.getBMI());
        values.put(InfoDbSchema.InfoTable.Cols.BFR, individualInfo.getBFR());
        values.put(InfoDbSchema.InfoTable.Cols.WAISTLINE, individualInfo.getWaistLine());
        values.put(InfoDbSchema.InfoTable.Cols.TEMPERATURE, individualInfo.getTemperature());
        values.put(InfoDbSchema.InfoTable.Cols.PULSE, individualInfo.getPulse());
        values.put(InfoDbSchema.InfoTable.Cols.BLOODPRESSURE, individualInfo.getBloodPressure());
        values.put(InfoDbSchema.InfoTable.Cols.BLOODSUGAR, individualInfo.getBloodSugar());
        values.put(InfoDbSchema.InfoTable.Cols.BLOODFAT, individualInfo.getBloodFat());
        return values;
    }
}
