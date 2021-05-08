package com.sjnono.bbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjnono.user.UserInfoController;
import com.sjnono.user.UserInfoResource;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    BbsService bbsService;

    @Autowired
    BbsValidator bbsValidator;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ModelAndView showBbsList(Bbs bbs){
        ModelAndView mav = new ModelAndView("bbs/bbs_list");
        List<Bbs> bbsList = this.bbsService.showBbsAll();
        List<BbsDto> bbsDtoList = new ArrayList<>();

        for (Bbs tempBbs: bbsList) {
            BbsDto bbsDto = modelMapper.map(tempBbs, BbsDto.class);
            bbsDto.setLink( linkTo(BbsController.class).slash(bbsDto.getId()).toString());
            bbsDtoList.add(bbsDto);

        }

        mav.addObject("bbsList",bbsDtoList);


        return mav;
    }

    @GetMapping("/{bbsId}")
    public ModelAndView showBbsDetail(@PathVariable("bbsId") Long bbsId){
        ModelAndView mav = new ModelAndView("bbs/bbs_detail");

        Bbs bbs = this.bbsService.findById(bbsId);
        BbsDto bbsDto = modelMapper.map(bbs, BbsDto.class);
        bbsDto.setLink( linkTo(BbsController.class).slash(bbsDto.getId()).toString());
        mav.addObject("bbs",bbs);


        return mav;

    }


    @PostMapping
    public ResponseEntity writeBbs(@RequestBody Bbs bbs, Errors errors){
        this.bbsValidator.writeValidate(bbs, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }
        Bbs newBbs = this.bbsService.writeBbs(bbs);

        LinkBuilder selfLinkBuilder = linkTo(BbsController.class).slash(newBbs.getId());
        URI createdUri = selfLinkBuilder.toUri();

        BbsResource userInfoResource = new BbsResource(newBbs, selfLinkBuilder.withSelfRel());


        return ResponseEntity.created(createdUri).body(userInfoResource);

    }



    @PutMapping("/{bbsId}")
    public ResponseEntity editBbs(@PathVariable("bbsId") Long bbsId, @RequestBody Bbs bbs, Errors errors){
        this.bbsValidator.editeValidate(bbs, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }
        bbs = this.bbsService.editBbs(bbsId,bbs);

        LinkBuilder selfLinkBuilder = linkTo(BbsController.class).slash(bbs.getId());
        URI createdUri = selfLinkBuilder.toUri();

        BbsResource userInfoResource = new BbsResource(bbs, selfLinkBuilder.withSelfRel());


        return ResponseEntity.created(createdUri).body(userInfoResource);
    }


    @DeleteMapping("/{bbsId}")
    public ResponseEntity deleteBbs(@PathVariable("bbsId") Long bbsId){

        this.bbsService.deleteBbs(bbsId);

        LinkBuilder selfLinkBuilder = linkTo(BbsController.class);
        URI createdUri = selfLinkBuilder.toUri();

        BbsResource userInfoResource = new BbsResource(Bbs.builder().build(), selfLinkBuilder.withSelfRel());


        return ResponseEntity.created(createdUri).body(userInfoResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        EntityModel<Errors> entityModel = EntityModel.of(errors).add(linkTo(this.getClass()).withSelfRel());
        return ResponseEntity.badRequest().body(entityModel);
    }

}
