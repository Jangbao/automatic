package com.boob.automatic.ytj;

import com.boob.automatic.entity.ClockConfig;
import com.boob.automatic.entity.ClockInfo;
import com.boob.automatic.entity.ClockToken;
import com.boob.automatic.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 易统计打卡需要的信息实体类
 *
 * @author jangbao - 2021/1/16 21:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YTJClockEntity {

    /**
     * 打卡用户
     */
    private User user;


    /**
     * 打卡配置
     */
    private ClockConfig clockConfig;


    /**
     * 打卡信息
     */
    private ClockInfo clockInfo;


    /**
     * 打卡需要的token
     */
    private ClockToken clockToken;


}
