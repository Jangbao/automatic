package com.boob.automatic.enums;

import com.boob.automatic.constants.TimeConstants;

import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
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

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
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


}
