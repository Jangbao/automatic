package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jangbao - 2021/1/19 21:54
 */
@Repository
public interface ClockResultDao extends JpaRepository<ClockResult, Long> {
}
