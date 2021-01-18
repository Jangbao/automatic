package com.boob.automatic.enums;

/**
 * 打卡类型枚举
 *
 * @author Jangbao
 */
public enum ClockTypeEnum {

    /**
     * 单独打卡
     */
    SINGLE("0"),
    /**
     * 集体打卡
     */
    GROUP("1"),

    ;

    private String code;

    ClockTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据code 获取 ClockTypeEnum
     *
     * @param code
     * @return code 不存在默认返回 GROUP
     */
    public static ClockTypeEnum getClockTypeEnumByCode(String code) {
        for (ClockTypeEnum clockTypeEnum : ClockTypeEnum.values()) {
            if (clockTypeEnum.getCode().equals(code)) {
                return clockTypeEnum;
            }
        }
        return GROUP;
    }

}
