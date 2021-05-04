package com.sjnono.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class UserInfoResource extends EntityModel<UserInfo> {

    public UserInfoResource(UserInfo userInfo, Link... links) {
        super(userInfo, links);
    }
}
