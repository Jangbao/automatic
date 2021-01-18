package com.boob.automatic.controller;

import com.alibaba.fastjson.JSON;
import com.boob.automatic.dao.ClockInfoDao;
import com.boob.automatic.dao.UserDao;
import com.boob.automatic.entity.ClockInfo;
import com.boob.automatic.entity.User;
import com.boob.automatic.service.IClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jangbao - 2021/1/14 18:31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClockInfoDao clockInfoDao;

    @Autowired
    private IClockService iClockService;


    @RequestMapping("user")
    public String user() {
        List<User> users = userDao.findAll();
        System.out.println(users);
        return JSON.toJSONString(users);
    }

    @RequestMapping("clock_info")
    public String clockInfo() {
        List<ClockInfo> clockInfos = clockInfoDao.findAll();
        System.out.println(clockInfos);
        return JSON.toJSONString(clockInfos);
    }

    @RequestMapping("run")
    public void run() {
//        iClockService.runClock();
    }

    @RequestMapping("shutdown")
    public void shutdown() {
//        iClockService.shutDownClock();
    }

    @RequestMapping("clock/{id}")
    public void clockByUserId(@PathVariable(name = "id") Long id) {
//        iClockService.clock(id);
    }

}
