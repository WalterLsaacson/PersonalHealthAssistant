package com.guanyin.sardar.pha.alert.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.guanyin.sardar.pha.alert.database.ClockDbSchema.CLockTable;

// 添加数据库的步骤
// 1.生成数据库的schema
// 2.新建类继承自SQLiteOpenHelper，负责更新和升级工作
// 3.在模型层替换原来的arrayList为当前的数据库（增删改查）
// 3.1：增加insert 3.2: 删除del 3.3 修改update
// 3.4 查询query   这里需要将数据库的数据和crime这个类对象进行相互转换
// 具体的为新建一个继承自cursorWrapper的类，封装转换的过程，返回一个crime对象
// 4.对于用户的操作只需要在模型层进行相关的增删改查 然后通知视图层进行刷新就可以了（关键就在于视图层刷新的时机）

// 继承自SQLiteOpenHelper，用于数据库的创建和更新工作
public class ClockBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "clocks.db";

    public ClockBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // 这里的代码只会执行一次，因此修改这里的代码时，需要卸载应用之后再重新运行
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 初始化的时候创建一个crimeTable
        db.execSQL("create table " + CLockTable.NAME + "(" +
                CLockTable.Cols.TITLE + "," +
                CLockTable.Cols.DATE + "," +
                CLockTable.Cols.OPENED + "," +
                CLockTable.Cols.ID + "," +
                CLockTable.Cols.MUSIC +
                ")"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('护牙提醒','9:00','0','0','')"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('运动提醒','18:00','0','1','')"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('量血压提醒','20:00','0','2','')"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('睡眠提醒','22:00','0','3','')"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('测体重提醒','07:00','0','4','')"
        );
        db.execSQL("insert into " + CLockTable.NAME
                + " values('服药提醒','13:00','0','5','')"
        );


//                + "insert into " + CLockTable.NAME
//                + "values('睡眠提醒','22:00','0','')"
//
//                + "insert into " + CLockTable.NAME
//                + "values('运动提醒','07:00','0','')"
//
//                + "insert into " + CLockTable.NAME
//                + "values('服药提醒','13:00','0','')"
//
//                + "insert into " + CLockTable.NAME
//                + "values('健身提醒','18:00','0','')"
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
