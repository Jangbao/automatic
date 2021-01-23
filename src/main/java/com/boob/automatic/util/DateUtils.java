package com.boob.automatic.util;

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
}
