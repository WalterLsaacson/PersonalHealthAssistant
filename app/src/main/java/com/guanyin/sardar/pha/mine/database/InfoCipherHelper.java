package com.guanyin.sardar.pha.mine.database;

import android.content.Context;

import com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.Cols;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

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
import static com.guanyin.sardar.pha.mine.database.InfoDbSchema.InfoTable.NAME;


// 添加数据库的步骤
// 1.生成数据库的schema
// 2.新建类继承自SQLiteOpenHelper，负责更新和升级工作
// 3.在模型层替换原来的arrayList为当前的数据库（增删改查）
// 3.1：增加insert 3.2: 删除del 3.3 修改update
// 3.4 查询query   这里需要将数据库的数据和crime这个类对象进行相互转换
// 具体的为新建一个继承自cursorWrapper的类，封装转换的过程，返回一个crime对象
// 4.对于用户的操作只需要在模型层进行相关的增删改查 然后通知视图层进行刷新就可以了（关键就在于视图层刷新的时机）

// 继承自SQLiteOpenHelper，用于数据库的创建和更新工作
public class InfoCipherHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "info.db";

    public InfoCipherHelper(Context context) {
        this(context, DATABASE_NAME, null, VERSION);
    }

    private InfoCipherHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
        //不可忽略的 进行so库加载
        SQLiteDatabase.loadLibs(context);
    }

    // 这里的代码只会执行一次，因此修改这里的代码时，需要卸载应用之后再重新运行
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 初始化的时候创建一个infoTable
        // BMI = 体重除以身高的平方
        // BFR = 1.2 * BMI + 0.23 * 年龄 - 5.4 - 10.8 *（M:1 W:0）
        db.execSQL("create table " + NAME + "(" +
                Cols.NAME + "," +
                AGE + "," +
                SEX + "," +
                HEIGHT + "," +
                WEIGHT + "," +
                BMI + "," +
                BFR + "," +
                WAISTLINE + "," +
                TEMPERATURE + "," +
                PULSE + "," +
                BLOODPRESSURE + "," +
                BLOODSUGAR + "," +
                BLOODFAT + "," +
                ID +
                ")"
        );
        db.execSQL("insert into " + NAME
                + " values('关印','21','男','178','65','','','','','','','','','0')"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
