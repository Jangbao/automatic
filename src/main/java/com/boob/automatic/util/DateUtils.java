package com.boob.automatic.util;

import com.boob.automatic.constants.TimeConstants;
import com.boob.automatic.enums.TimeSlotEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
import java.util.Random;

/**
 * @author jangbao - 2021/1/20 22:40
 */
public class DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

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
     * 解析时间字符串
     *
     * @param dateString 时间字符串
     * @return
     */
    public static Date parse(String dateString) {
        return parse(dateString, DEFAULT_DATE_PATTERN);
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
     * 解析时间字符串
     *
     * @param dateString 时间字符串
     * @param pattern    时间字符串格式
     * @return
     */
    public static Date parse(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("解析时间字符串失败：dateString {" + dateString + "}  , pattern{" + pattern + "}");
        }
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


    /**
     * 获取今天某个时间段内的随机时间
     *
     * @return
     */
    public static Date todayTimeSlot(TimeSlotEnum timeSlotEnum) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 获取随机时间
        Random random = new Random();
        int hour = random.nextInt(timeSlotEnum.getEndTime() - timeSlotEnum.getStartTime()) + timeSlotEnum.getStartTime();
        int second = random.nextInt(TimeConstants.SECOND_GAP);
        int millisSecond = random.nextInt(TimeConstants.MILLI_SECOND_GAP);

        return new Builder()
                .setDate(year, month, day)
                .setTimeOfDay(hour, second, millisSecond)
                .build()
                .getTime();
    }


    /**
     * 获取明天某个时间段内的随机时间
     *
     * @return
     */
    public static Date tomorrowTimeSlot(TimeSlotEnum timeSlotEnum) {
        return new Date(todayTimeSlot(timeSlotEnum).getTime() + TimeConstants.ONE_DAY_TO_MILLIS);
    }

    /**
     * 到达指定时间需要等待的时间(单位 毫秒)
     *
     * @return
     */
    public static long getTimeToWait(Date date) {
        long currentTime = System.currentTimeMillis();
        return (date.getTime() - currentTime);
    }

}
