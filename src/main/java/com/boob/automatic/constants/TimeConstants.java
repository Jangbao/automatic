package com.boob.automatic.constants;

/**
 * 时间常量
 *
 * @author jangbao - 2021/1/14 23:01
 */
public class TimeConstants {

    /**
     * 计算机初始年份
     */
    public static final int START_YEAR = 1900;

    /**
     * 一年有多少月
     */
    public static final int MONTH_GAP = 12;

    /**
     * 一天有多少小时
     */
    public static final int HOUR_GAP = 24;

    /**
     * 一小时有多少分钟
     */
    public static final int MINUTE_GAP = 60;

    /**
     * 一分钟有多少秒
     */
    public static final int SECOND_GAP = 60;

    /**
     * 一秒有多少毫秒
     */
    public static final int MILLI_SECOND_GAP = 1000;


    /**
     * 一小时等于多少秒
     */
    public static final int ONE_HOUR_TO_SECOND = 60 * 60;
    /**
     * 一小时等于多少秒
     */
    public static final int ONE_HOUR_TO_MILLIS = ONE_HOUR_TO_SECOND * MILLI_SECOND_GAP;

    /**
     * 一天等于多少秒
     */
    public static final int ONE_DAY_TO_SECOND = ONE_HOUR_TO_SECOND * 24;

    /**
     * 一天等于多少毫秒
     */
    public static final int ONE_DAY_TO_MILLIS = ONE_DAY_TO_SECOND * MILLI_SECOND_GAP;


}
