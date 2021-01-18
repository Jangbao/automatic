package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author jangbao - 2021/1/14 18:57
 */
@Repository
public interface ClockInfoDao extends JpaRepository<ClockInfo, Long> {

    /**
     * 根据userId查询
     *
     * @param userId
     * @return ClockInfo
     */
    ClockInfo findByUserId(Long userId);

    /**
     * 查询userId 在集合中的
     *
     * @param userIds
     * @return
     */
    List<ClockInfo> findAllByUserIdIn(Collection<Long> userIds);
}
