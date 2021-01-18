package com.boob.automatic.ytj;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 易统计打卡请求信息
 *
 * @author jangbao - 2021/1/16 23:22
 */
@Data
@AllArgsConstructor
public class YTJRequest {

    /**
     * 已统计打卡信息
     */
    private YTJSend ytjSend;

    /**
     * 需要的token信息
     */
    private YTJToken ytjToken;

}
