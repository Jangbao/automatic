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
import java.util.Date;

/**
 * @author jangbao - 2021/1/13 22:40
 */
@Entity
@Table(name = "clock_config")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SQLDelete(sql = "update clock_config set is_deleted = '1' where id = ?")
@Where(clause = "is_deleted = 0")
public class ClockConfig extends BaseEntity implements Serializable {

    /**
     * 打卡时间段 0:上午，1:中午，3:下午,4:晚上
     */
    @Column(name = "clock_time_quantum")
    private String clockTimeQuantum;

    /**
     * 最后一次打卡时间
     */
    @Column(name = "clock_end_date")
    private Date clockEndDate;

    /**
     * 打卡开关 0:关，1：开
     */
    @Column(name = "clock_open")
    private String clockOpen;

    @Column(name = "user_id")
    private Long userId;
}
