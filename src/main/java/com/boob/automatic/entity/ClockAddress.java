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
 * 数据库地址配置
 *
 * @author jangbao - 2021/1/13 22:32
 */

@Entity
@Table(name = "clock_address")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SQLDelete(sql = "update clock_address set is_deleted = '1' where id = ?")
@Where(clause = "is_deleted = 0")
public class ClockAddress extends BaseEntity implements Serializable {

    /**
     * #省份代码（湖北省）
     */
    @Column(name = "province")
    private String province;

    /**
     * #市区代码（武汉市）
     */
    @Column(name = "city")
    private String city;

    /**
     * #县级代码（江夏区）
     */
    private String county;

    @Column(name = "auto_fetch")
    private boolean autoFetch;

    /**
     * #填写当前地区经度
     */
    @Column(name = "lng")
    private String lng;

    /**
     * #填写当前地区纬度
     */
    @Column(name = "lat")
    private String lat;


}
