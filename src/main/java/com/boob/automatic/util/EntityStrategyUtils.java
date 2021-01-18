package com.boob.automatic.util;

import com.boob.automatic.entity.ClockAddress;
import com.boob.automatic.entity.ClockConfig;
import com.boob.automatic.entity.ClockInfo;
import com.boob.automatic.enums.OnOffEnum;
import com.boob.automatic.enums.TimeSlotEnum;

/**
 * 易统计信息生成策略
 *
 * @author jangbao - 2021/1/12 22:49
 */
public class EntityStrategyUtils {

    /**
     * 默认策略设置clockInfo
     *
     * @return
     */
    public static void strategyClockInfo(ClockInfo clockInfo) {
        clockInfo.setAt_home(true)
                .setContacted(false)
                .setContacted_beijing(false)
                .setDescription("")
                .setFamily_confirmed(false)
                .setFamily_suspected(false)
                .setFever(false)
                .setInfected(false)
                .setPassed_beijing(false)
                .setSelf_confirmed(false)
                .setSelf_suspected(false);
    }

    /**
     * 默认策略设置clockAddress
     *
     * @return
     */
    public static void strategyClockAddress(ClockAddress clockAddress) {
        clockAddress.setAutoFetch(true);
    }


    /**
     * 默认策略设置clockConfig
     *
     * @return
     */
    public static void strategyClockConfig(ClockConfig clockConfig) {
        clockConfig.setClockOpen(OnOffEnum.ON.getCode())
                .setClockTimeQuantum(TimeSlotEnum.MORNING.getCode());

    }
}
