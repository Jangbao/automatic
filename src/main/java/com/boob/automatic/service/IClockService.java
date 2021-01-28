package com.boob.automatic.service;

import com.boob.automatic.entity.ClockResult;

import java.util.List;

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
    String clockByUserId(Long userId);

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

    /**
     * 今日群体打卡信息
     *
     * @return
     */
    List<ClockResult> todayGroupClockInfo();

    /**
     * 今日个人打卡信息
     *
     * @return
     */
    List<ClockResult> todaySingleClockInfo();

    /**
     * 今天指定用户的打卡信息
     *
     * @param userId
     * @return
     */
    List<ClockResult> todayUserClockInfo(Long userId);

    /**
     * 用户打卡信息
     *
     * @param userId
     * @return
     */
    List<ClockResult> userClockInfo(Long userId);
}
