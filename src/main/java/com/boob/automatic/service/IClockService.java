package com.boob.automatic.service;

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
     * 给指定用户打卡
     *
     * @param userId 指定用户id
     * @return
     */
    boolean clock(Long userId);

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
