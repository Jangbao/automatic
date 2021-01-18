package com.boob.automatic.dao;

import com.boob.automatic.entity.ClockAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jangbao - 2021/1/14 21:54
 */
public interface ClockAddressDao extends JpaRepository<ClockAddress, Long> {
}
