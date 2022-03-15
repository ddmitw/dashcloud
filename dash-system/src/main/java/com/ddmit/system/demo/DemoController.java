package com.ddmit.system.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping(value = "/users")
    public void synchronizeAllDepts() {
        System.out.println("获取用户信息……");
    }
}
