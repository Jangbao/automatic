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
 * 打卡结果信息
 *
 * @author jangbao - 2021/1/16 22:40
 */
@Entity
@Table(name = "clock_result")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SQLDelete(sql = "update clock_result set is_deleted = '1' where id = ?")
@Where(clause = "is_deleted = 0")
public class ClockResult extends BaseEntity implements Serializable {

    /**
     * 打卡类型(0 单独打卡 1 集体打卡)
     */
    @Column(name = "clock_type")
    private String clockType;

    /**
     * 打卡用户id
     */
    @Column(name = "clock_user_id")
    private Long clockUserId;

    /**
     * 请求消息 json
     */
    @Column(name = "request_message")
    private String requestMessage;

    /**
     * 返回消息 json
     */
    @Column(name = "response_message")
    private String responseMessage;

    /**
     * 打卡时间
     */
    @Column(name = "clock_time")
    private Date clockTime;

    /**
     * 操作人(自动打卡 id 为0)
     */
    @Column(name = "operate_user_id")
    private Long operateUserId;

    /**
     * 是否操作成功(0 失败 1 成功)
     */
    @Column(name = "success")
    private boolean success;
}
