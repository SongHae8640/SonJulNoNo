package com.sjnono.index;

import com.sjnono.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    UserInfoService userInfoService;



    @GetMapping
    public ModelAndView getUserInfoList(Map<String, Object> model){
        ModelAndView mav = new ModelAndView("index");

        //List<UserInfo> userInfoList = userInfoService.findAll();
        //mav.addObject(userInfoList);

        return mav;
    }

}
