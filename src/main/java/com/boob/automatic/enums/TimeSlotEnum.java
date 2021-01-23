package com.boob.automatic.enums;

import com.boob.automatic.contants.TimeConstants;

import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 时间枚举类
 *
 * @author Jangbao
 */
public enum TimeSlotEnum {

    /**
     * 早上
     */
    MORNING("0", 7, 11),
    /**
     * 中午
     */
    NOON("1", 11, 13),
    /**
     * 下午
     */
    AFTERNOON("2", 13, 17),
    /**
     * 晚上
     */
    EVENING("3", 17, 23),
    ;

    /**
     * 编码
     */
    private String code;

    /**
     * 从几点开始
     */
    private int startTime;

    /**
     * 到几点结束
     */
    private int endTime;

    TimeSlotEnum(String code, int startTime, int endTime) {
        this.code = code;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据code 获取 timeSlotEnum
     *
     * @param code
     * @return code 不存在默认返回 MORNING
     */
    public static TimeSlotEnum getTimeSlotEnumByCode(String code) {
        for (TimeSlotEnum timeSlotEnum : TimeSlotEnum.values()) {
            if (timeSlotEnum.getCode().equals(code)) {
                return timeSlotEnum;
            }
        }
        return MORNING;
    }

    /**
     * 枚举类型数量
     *
     * @return
     */
    public static int typeNum() {
        return TimeSlotEnum.values().length;
    }

    /**
     * 获取随机打卡时间
     *
     * @return
     */
    public Date getClockTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 获取随机时间
        Random random = new Random();
        int hour = random.nextInt(endTime - startTime) + startTime;
        int second = random.nextInt(TimeConstants.SECOND_GAP);
        int millisSecond = random.nextInt(TimeConstants.MILLI_SECOND_GAP);

        return new Builder()
                .setDate(year, month, day)
                .setTimeOfDay(hour, second, millisSecond)
                .build()
                .getTime();
    }

    /**
     * 获取到达打卡时间需要等待的时间(单位 秒)
     *
     * @return
     */
    public long getTimeToWait() {

        long clockTime = getClockTime().getTime();
        long currentTime = System.currentTimeMillis();

        long timeToWait = (clockTime - currentTime) / TimeConstants.MILLI_SECOND_GAP;
        return timeToWait;
    }

}
