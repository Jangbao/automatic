package com.boob.automatic.controller.admin;

import com.boob.automatic.service.IClockService;
import com.boob.automatic.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jangbao - 2021/1/17 21:13
 */
@RestController
@RequestMapping("/admin/clock")
public class ClockController {

    @Autowired
    private IClockService clockService;

    @RequestMapping("run")
    public Result run() {
        clockService.runClock();
        if (clockService.isRunning()) {
            return Result.success("开启打卡成功");
        }
        return Result.fail("开启打卡失败");
    }

    @RequestMapping("shutdown")
    public Result shutdown() {
        clockService.shutDownClock();
        if (clockService.isRunning()) {
            return Result.fail("关闭打卡失败");
        }
        return Result.success("关闭打卡成功");

    }

    @RequestMapping("is_running")
    public Result isRunning() {
        if (clockService.isRunning()) {
            return Result.success("打卡功能开启");
        }
        return Result.success("打卡功能未开启");
    }

    @RequestMapping("single_clock/{id}")
    public Result clockByUserId(@PathVariable(name = "id") Long id) {
        return Result.success(clockService.clockByUserId(id));
    }

    @RequestMapping("today_group_clock_info")
    public Result todayGroupClockInfo() {
        return Result.success(clockService.todayGroupClockInfo());
    }

    @RequestMapping("today_single_clock_info")
    public Result todaySingleClockInfo() {
        return Result.success(clockService.todaySingleClockInfo());
    }

    @RequestMapping("today_user_clock_info/{id}")
    public Result todayUserClockInfo(@PathVariable(name = "id") Long id) {
        return Result.success(clockService.todayUserClockInfo(id));
    }

    @RequestMapping("user_clock_info/{id}")
    public Result userClockInfo(@PathVariable(name = "id") Long id) {
        return Result.success(clockService.userClockInfo(id));
    }
}
