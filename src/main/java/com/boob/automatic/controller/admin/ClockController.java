package com.boob.automatic.controller.admin;

import com.boob.automatic.service.IClockService;
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
    public void run() {
        clockService.runClock();
    }

    @RequestMapping("shutdown")
    public void shutdown() {
        clockService.shutDownClock();
    }

    @RequestMapping("single_clock/{id}")
    public void clockByUserId(@PathVariable(name = "id") Long id) {
        clockService.clock(id);
    }
}
