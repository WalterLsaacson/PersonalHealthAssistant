package com.guanyin.sardar.pha.status.database;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.Cols;
import static com.guanyin.sardar.pha.status.database.DayDbSchema.DayTable.NAME;


public class DayCipherHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "day.db";

    public DayCipherHelper(Context context) {
        this(context, DATABASE_NAME, null, VERSION);
    }

    private DayCipherHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        //不可忽略的 进行so库加载
        SQLiteDatabase.loadLibs(context);
    }

    // 这里的代码只会执行一次，因此修改这里的代码时，需要卸载应用之后再重新运行
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + "(" +
                Cols.DATE + " int ," +
                Cols.SLEEP + "," +
                Cols.WATER + "," +
                Cols.SPORT +
                ")"
        );
        db.execSQL("insert into " + NAME
                + " values('20170401','460','1430','47')"
        );
        db.execSQL("insert into " + NAME
                + " values('20170421','540','1390','38')"
        );
        db.execSQL("insert into " + NAME
                + " values('20170406','480','1690','43')"
        );
        db.execSQL("insert into " + NAME
                + " values('20170418','433','1560','40')"
        );
        db.execSQL("insert into " + NAME
                + " values('20170415','534','1810','59')"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
