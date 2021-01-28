package com.boob.automatic.util;

import com.boob.automatic.constants.TimeConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jangbao - 2021/1/20 22:40
 */
public class DateUtils {

    /**
     * 默认时间格式
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return
     */
    public static String format(Date date) {
        return format(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 格式化时间
     *
     * @param date    时间
     * @param pattern 时间格式
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 获取今天 0：00 时刻的date
     *
     * @return
     */
    public static Date today() {
        // 先获取当天早上8:00 的时间,再减去 8个小时
        Date date = new Date((System.currentTimeMillis() / TimeConstants.ONE_DAY_TO_MILLIS) * TimeConstants.ONE_DAY_TO_MILLIS - 8 * TimeConstants.ONE_HOUR_TO_MILLIS);
        return date;
    }
}
