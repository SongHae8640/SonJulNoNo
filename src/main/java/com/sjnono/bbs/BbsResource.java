package com.sjnono.bbs;

import com.sjnono.user.UserInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class BbsResource extends EntityModel<Bbs> {

    public BbsResource(Bbs bbs, Link... links) {
        super(bbs, links);
    }
}
