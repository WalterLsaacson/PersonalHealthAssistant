package com.guanyin.sardar.personalhealthassistant.database;

// 当前类用于创建该应用的数据库字段名称 即：数据库模型
public class ClockDbSchema {
    public static final class CLockTable {
        public static final String NAME = "clocks";

        public static final class Cols {
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String OPENED = "opened";
            public static final String MUSIC = "music";
            public static final String ID = "id";
        }
    }
}
