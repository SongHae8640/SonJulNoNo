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

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.http.ResponseEntity.*;

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
        ModelAndView mav = new ModelAndView("main");
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
    public ResponseEntity insertUserInfo(@RequestBody @Valid UserInfoDto userInfoDto
                                        , Errors errors){
        userValidator.joinValidate(userInfoDto, errors);
        if (errors.hasErrors()){
            return badRequest(errors);
        }

        UserInfo validUserInfo = modelMapper.map(userInfoDto, UserInfo.class);
        validUserInfo = userInfoService.joinUser(validUserInfo, errors);
        if (errors.hasErrors()){
            return badRequest(errors);
        }


        LinkBuilder selfLinkBuilder = linkTo(UserInfoController.class).slash(validUserInfo.getId());
        URI createdUri = selfLinkBuilder.toUri();

        UserInfoResource userInfoResource = new UserInfoResource(validUserInfo, selfLinkBuilder.withSelfRel());


        return created(createdUri).body(userInfoResource);
    }

    @GetMapping("/login")
    public ModelAndView goLoginPage(){
        ModelAndView mav = new ModelAndView("user/login");

        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("user/login");

        return mav;
    }


    private ResponseEntity badRequest(Errors errors) {
        EntityModel<Errors> entityModel = EntityModel.of(errors).add(linkTo(this.getClass()).slash("join").withSelfRel());
        return ResponseEntity.badRequest().body(entityModel);
    }

}
