package com.boob.automatic.controller.admin;

import com.boob.automatic.service.IClockService;
import com.boob.automatic.util.Result;
import com.boob.automatic.ytj.YTJResult;
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
            return Result.success("run success");
        }
        return Result.fail("run fail");
    }

    @RequestMapping("shutdown")
    public Result shutdown() {
        clockService.shutDownClock();
        if (clockService.isRunning()) {
            return Result.fail("shutdown fail");
        }
        return Result.success("shutdown success");

    }

    @RequestMapping("is_running")
    public Result isRunning() {
        if (clockService.isRunning()) {
            return Result.success("is running");
        }
        return Result.success("not running");
    }

    @RequestMapping("single_clock/{id}")
    public YTJResult clockByUserId(@PathVariable(name = "id") Long id) {
        return clockService.clock(id);
    }
}
