package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockResult;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author jangbao - 2021/1/19 21:54
 */
@Repository
public interface ClockResultDao extends JpaRepository<ClockResult, Long> {

    /**
     * 查询指定用户打卡信息
     *
     * @param userId 用户id
     * @return
     */
    List<ClockResult> findByClockUserId(Long userId);

    /**
     * 查询指定用户超过指定时间打卡的信息
     *
     * @param userId    用户id
     * @param clockTime 打卡时间
     * @return
     */
    List<ClockResult> findByClockUserIdAndClockTimeGreaterThan(Long userId, Date clockTime);

    /**
     * 查询指定打卡类型超过指定时间打卡的信息
     *
     * @param clockType 打卡类型
     * @param clockTime 打卡时间
     * @return
     */
    List<ClockResult> findByClockTypeAndClockTimeGreaterThan(String clockType, Date clockTime);


}
