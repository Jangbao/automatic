package com.boob.automatic.ytj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 易统计token
 *
 * @author jangbao - 2021/1/12 22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class YTJToken {

    /**
     * 用户token
     */
    private String token;

}
