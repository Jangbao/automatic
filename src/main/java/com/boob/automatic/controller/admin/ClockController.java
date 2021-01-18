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

        return Result.success("run success");
    }

    @RequestMapping("shutdown")
    public Result shutdown() {
        clockService.shutDownClock();

        return Result.success("shutdown success");
    }

    @RequestMapping("single_clock/{id}")
    public Result clockByUserId(@PathVariable(name = "id") Long id) {
        clockService.clock(id);

        return Result.success("clock success");
    }
}
