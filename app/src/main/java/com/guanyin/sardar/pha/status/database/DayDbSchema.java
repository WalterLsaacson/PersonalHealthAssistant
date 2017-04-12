package com.guanyin.sardar.pha.status.database;

// 当前类用于创建该应用的数据库字段名称 即：数据库模型
public class DayDbSchema {
    public static final class DayTable {
        public static final String NAME = "everyday";

        public static final class Cols {
            public static final String DATE = "date";
            public static final String WATER = "water";
            public static final String SLEEP = "sleep";
            public static final String SPORT = "sport";
        }
    }
}
