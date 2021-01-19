package com.boob.automatic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author jangbao - 2021/1/13 22:39
 */
@Entity
@Table(name = "clock_info")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SQLDelete(sql = "update clock_info set is_deleted = '1' where id = ?")
@Where(clause = "is_deleted = 0")
public class ClockInfo extends BaseEntity implements Serializable {

    /**
     * 地址信息
     */
    @OneToOne
    @JoinColumn(name = "clock_address_id", referencedColumnName = "id")
    private ClockAddress address;

    @Column(name = "self_suspected")
    private boolean self_suspected;

    @Column(name = "self_confirmed")
    private boolean self_confirmed;

    @Column(name = "family_suspected")
    private boolean family_suspected;

    @Column(name = "family_confirmed")
    private boolean family_confirmed;
    /**
     * #是否发热
     */
    @Column(name = "fever")
    private boolean fever;

    @Column(name = "description")
    private String description;
    /**
     * #是否感染
     */
    @Column(name = "infected")
    private boolean infected;

    @Column(name = "at_home")
    private boolean at_home;

    @Column(name = "contacted_beijing")
    private boolean contacted_beijing;

    @Column(name = "passed_beijing")
    private boolean passed_beijing;

    @Column(name = "contacted")
    private boolean contacted;

    @Column(name = "user_id")
    private Long userId;
}
