package com.sjnono.user;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    ModelMapper modelMapper;



    @GetMapping("/{id}")
    public ModelAndView getUserInfo(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("index");
        UserInfo userInfo = this.userInfoService.findById(id);

        mav.addObject(userInfo);
        return mav;
    }

    @GetMapping("/join")
    public ModelAndView goJoinPage(){
        ModelAndView mav = new ModelAndView("user/join");

        return mav;



    }

    @PostMapping
    public ResponseEntity insertUserInfo(@RequestBody UserInfoDto userInfoDto, Errors errors){
        userValidator.joinValidate(userInfoDto, errors);
        if (errors.hasErrors()){

            return badRequest(errors);
        }

        UserInfo newUserInfo = modelMapper.map(userInfoDto, UserInfo.class);
        newUserInfo = userInfoService.joinUser(newUserInfo, errors);
        if (errors.hasErrors()){
            return badRequest(errors);
        }


        LinkBuilder selfLinkBuilder = linkTo(UserInfoController.class).slash(newUserInfo.getId());
        URI createdUri = selfLinkBuilder.toUri();

        UserInfoResource userInfoResource = new UserInfoResource(newUserInfo, selfLinkBuilder.withSelfRel());


        return ResponseEntity.created(createdUri).body(userInfoResource);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserInfoDto userInfoDto, Errors errors){
        userValidator.loginValidate(userInfoDto,errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }


        UserInfo loginUserInfo = modelMapper.map(userInfoDto, UserInfo.class);
        loginUserInfo = userInfoService.loginUser(loginUserInfo, errors);
        if (errors.hasErrors()){
            return badRequest(errors);
        }


        LinkBuilder selfLinkBuilder = linkTo(UserInfoController.class).slash(loginUserInfo.getId());
        URI createdUri = selfLinkBuilder.toUri();

        UserInfoResource userInfoResource = new UserInfoResource(loginUserInfo, selfLinkBuilder.withSelfRel());


        return ResponseEntity.created(createdUri).body(userInfoResource);
    }

    @GetMapping("/login")
    public ModelAndView goLoginPage(){
        ModelAndView mav = new ModelAndView("user/login");

        return mav;
    }





    private ResponseEntity badRequest(Errors errors) {
        EntityModel<Errors> entityModel = EntityModel.of(errors).add(linkTo(this.getClass()).slash("join").withSelfRel());
        return ResponseEntity.badRequest().body(entityModel);
    }

}
