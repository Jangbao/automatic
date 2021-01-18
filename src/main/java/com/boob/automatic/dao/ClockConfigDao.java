package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author jangbao - 2021/1/14 21:54
 */
@Repository
public interface ClockConfigDao extends JpaRepository<ClockConfig, Long> {

    /**
     * 根据userId查询
     *
     * @param userId
     * @return ClockInfo
     */
    ClockConfig findByUserId(Long userId);

    /**
     * 查询userId 在集合中的
     *
     * @param userIds
     * @return
     */
    List<ClockConfig> findAllByUserIdIn(Collection<Long> userIds);
}
