package com.guanyin.sardar.personalhealthassistant.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.guanyin.sardar.personalhealthassistant.database.ClockDbSchema.CLockTable;
import com.guanyin.sardar.personalhealthassistant.model.Clock;

import java.util.Date;

// 这个类存在的意义是封装转换的方法
public class ClockCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ClockCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Clock getClock() {
        // 从数据库对象到clock对象的转换
        String title = getString(getColumnIndex(CLockTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CLockTable.Cols.DATE));
        int isOpened = getInt(getColumnIndex(CLockTable.Cols.OPENED));
        String music = getString(getColumnIndex(CLockTable.Cols.MUSIC));

        // 创建clock对象
        Clock clock = new Clock();
        clock.setDate(new Date(date));
        clock.setTitle(title);
        clock.setOpen(isOpened != 0);
        clock.setMusic(music);

        return clock;
    }
}
