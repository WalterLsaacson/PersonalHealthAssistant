package com.guanyin.sardar.pha.alert.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.guanyin.sardar.pha.alert.database.ClockDbSchema.CLockTable;
import com.guanyin.sardar.pha.alert.model.Clock;

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
        String date = getString(getColumnIndex(CLockTable.Cols.DATE));
        int isOpened = getInt(getColumnIndex(CLockTable.Cols.OPENED));
        String id = getString(getColumnIndex(CLockTable.Cols.ID));
        String music = getString(getColumnIndex(CLockTable.Cols.MUSIC));

        // 创建clock对象
        Clock clock = new Clock();
        clock.setTitle(title);
        clock.setDate(date);
        clock.setOpen(isOpened != 0);
        clock.setId(id);
        clock.setMusic(music);

        return clock;
    }
}
