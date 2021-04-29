package com.sjnono.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;



    @GetMapping
    public String getUserInfoList(Map<String, Object> model){
        List<UserInfo> userInfoList = userInfoService.findAll();

        model.put("userInfoList", userInfoList);

        return "main";
    }

    @GetMapping("/{id}")
    public ModelAndView getUserInfo(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("main");
        UserInfo userInfo = this.userInfoService.findById(id);

        mav.addObject(userInfo);
        return mav;
    }

    @PostMapping
    public String insertUserInfo(@RequestBody UserInfo userInfo){
        UserInfo newUserInfo = this.userInfoService.save(userInfo);


        return "redirect:/user/"+newUserInfo.getId();
    }
}
