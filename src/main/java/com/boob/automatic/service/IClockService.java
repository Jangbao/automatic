package com.boob.automatic.service;

import com.boob.automatic.ytj.YTJResult;

/**
 * @author jangbao - 2021/1/5 18:30
 */
public interface IClockService {

    /**
     * 启动打卡
     */
    void runClock();

    /**
     * 关闭打卡
     */
    void shutDownClock();

    /**
     * 是否在运行中
     *
     * @return
     */
    boolean isRunning();

    /**
     * 给指定用户打卡
     *
     * @param userId 指定用户id
     * @return
     */
    YTJResult clock(Long userId);

    /**
     * 撤销打卡用户
     *
     * @param userId
     * @return
     */
    boolean cancelClockUser(Long userId);

    /**
     * 增加打卡用户
     *
     * @param userId
     * @return
     */
    boolean addClockUser(Long userId);

}
