package com.boob.automatic.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jangbao - 2021/1/28 22:53
 */
public class ThreadUtils {

    /**
     * 线程睡眠
     *
     * @param sleepTime 睡眠时长
     * @param timeUnit  时间单位
     */
    public static void sleep(long sleepTime, TimeUnit timeUnit) {
        try {
            //等待随机秒数后继续下一次打卡
            timeUnit.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程睡眠,以秒为单位
     *
     * @param sleepTime 睡眠时长
     */
    public static void sleepSeconds(long sleepTime) {
        sleep(sleepTime, TimeUnit.SECONDS);
    }

    /**
     * 线程睡眠随机时间
     *
     * @param maxTime  睡眠最大时长
     * @param timeUnit 时间单位
     */
    public static void sleepRandom(int maxTime, TimeUnit timeUnit) {
        Random random = new Random(maxTime);
        sleep(random.nextLong(), timeUnit);
    }

    /**
     * 线程睡眠随机时间,以秒为单位
     *
     * @param maxTime 睡眠最大时长
     */
    public static void sleepSecondsRandom(int maxTime) {
        sleepRandom(maxTime, TimeUnit.SECONDS);
    }

}
