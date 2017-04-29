package com.guanyin.sardar.pha.mine.database;

// 当前类用于创建该应用的数据库字段名称 即：数据库模型
public class InfoDbSchema {
    public static final class InfoTable {
        public static final String NAME = "info";

        public static final class Cols {
            public static final String AGE = "age";
            public static final String SEX = "sex";
            public static final String NAME = "name";
            public static final String HEIGHT = "height";
            public static final String WEIGHT = "weight";
            public static final String ID = "id";

            public static final String WAISTLINE = "waistLine";
            public static final String PULSE = "pulse";
            public static final String SYSTOLICRESSURE = "systolicPressure";
            public static final String DIASTOLICPRESSURE = "diastolicPressure";
            public static final String BLOODSUGAR = "bloodSugar";
            public static final String TC = "tc";

            public static final String WATERINTAKE = "waterIntake";
            public static final String SLEEPTIME = "sleepTime";
            public static final String RUNDURATION = "runDuration";
            public static final String HEALTHMARK = "healthMark";


        }
    }
}
