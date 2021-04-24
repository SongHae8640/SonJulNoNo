package com.sjnono.bbs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/bbs")
public class BbsRestController {
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
