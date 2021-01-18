package com.boob.automatic.enums;

/**
 * 开关枚举类
 *
 * @author jangbao - 2021/1/14 22:14
 */
public enum OnOffEnum {


    /**
     * 开
     */
    ON("1"),
    /**
     * 关
     */
    OFF("0"),

    ;

    private String code;

    OnOffEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据code 获取onOffEnum
     *
     * @param code
     * @return code 不存在默认返回 OFF
     */
    public static OnOffEnum getOnOffEnumByCode(String code) {
        for (OnOffEnum onOffEnum : OnOffEnum.values()) {
            if (onOffEnum.getCode().equals(code)) {
                return onOffEnum;
            }
        }
        return OFF;
    }

    /**
     * 是否开启
     */
    public boolean isOn() {
        return this.equals(OnOffEnum.ON);
    }

    /**
     * 是否关闭
     */
    public boolean isOff() {
        return this.equals(OnOffEnum.OFF);
    }
}
