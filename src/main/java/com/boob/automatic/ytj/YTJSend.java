package com.boob.automatic.ytj;

import com.boob.automatic.util.Send;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 易统计请求
 *
 * @author jangbao - 2021/1/12 22:14
 */
@Data
@Accessors(chain = true)
public class YTJSend implements Send, Serializable {

    /**
     * 地址信息
     */
    private YTJAddress address;

    private boolean self_suspected;
    private boolean self_confirmed;
    private boolean family_suspected;
    private boolean family_confirmed;
    /**
     * #是否发热
     */
    private boolean fever;

    private String description;
    /**
     * #是否感染
     */
    private boolean infected;
    private boolean at_home;
    private boolean contacted_beijing;
    private boolean passed_beijing;
    private boolean contacted;


    @Override
    public Object getParams() {
        return this;
    }
}
