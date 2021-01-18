package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author jangbao - 2021/1/14 21:53
 */
@Repository
public interface ClockTokenDao extends JpaRepository<ClockToken, Long> {

    /**
     * 根据userId查询
     *
     * @param userId
     * @return ClockInfo
     */
    ClockToken findByUserId(Long userId);

    /**
     * 查询userId 在集合中的
     *
     * @param userIds
     * @return
     */
    List<ClockToken> findAllByUserIdIn(Collection<Long> userIds);
}
