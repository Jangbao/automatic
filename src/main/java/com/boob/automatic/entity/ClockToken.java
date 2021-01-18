package com.boob.automatic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author jangbao - 2021/1/14 12:50
 */
@Entity
@Table(name = "clock_token")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SQLDelete(sql = "update clock_token set is_deleted = '1' where id = ?")
@Where(clause = "is_deleted = 0")
public class ClockToken extends BaseEntity implements Serializable {

    @Column(name = "token")
    private String token;

    @Column(name = "user_id")
    private Long userId;
}
