package com.boob.automatic.ytj;

import com.boob.automatic.util.R;
import lombok.Data;

/**
 * 易统计返回消息
 * @author jangbao - 2021/1/19 22:39
 */
@Data
public class YTJResult implements R {

    private String code;

    private String msg;

    private String errors;

    private String data;

}
