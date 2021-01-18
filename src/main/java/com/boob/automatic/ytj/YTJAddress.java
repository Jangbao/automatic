package com.boob.automatic.ytj;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 易统计请求地址
 *
 * @author jangbao - 2021/1/12 22:37
 */
@Data
@Accessors(chain = true)
public class YTJAddress implements Serializable {

    /**
     * #省份代码（湖北省）
     */
    private String province;
    /**
     * #市区代码（武汉市）
     */
    private String city;
    /**
     * #县级代码（江夏区）
     */
    private String county;

    private boolean autoFetch;
    /**
     * #填写当前地区经度
     */
    private String lng;
    /**
     * #填写当前地区纬度
     */
    private String lat;
}
