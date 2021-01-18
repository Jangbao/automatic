package com.boob.automatic.dao;

import com.boob.automatic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jangbao - 2021/1/14 18:32
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
