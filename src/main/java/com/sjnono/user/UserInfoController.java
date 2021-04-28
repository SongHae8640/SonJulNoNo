package com.sjnono.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @GetMapping
    public String getUserInfoList(Model model){
        List<UserInfo> userInfoList = userInfoService.findAll();
        model.addAllAttributes(userInfoList);

        return "main";
    }

    @PostMapping
    public String insertUserInfo(@RequestBody UserInfo userInfo){
        UserInfo newUserInfo = this.userInfoService.save(userInfo);


        return "main";
    }
}
