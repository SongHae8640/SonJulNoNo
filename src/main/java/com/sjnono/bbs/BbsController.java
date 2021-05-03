package com.sjnono.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    BbsService bbsService;

    @GetMapping
    public ModelAndView showBbsList(Bbs bbs){
        ModelAndView mav = new ModelAndView("bbs/bbs_list");
        List<Bbs> bbsList = this.bbsService.showBbsAll(bbs.getTitle());

        mav.addObject(bbsList);

        return mav;
    }

    @GetMapping("/{bbsId}")
    public ModelAndView showBbsDetail(@PathVariable("bbsId") Long bbsId){
        ModelAndView mav = new ModelAndView("bbs/bbs_detail");

        Bbs bbs = this.bbsService.findById(bbsId);
        mav.addObject(bbs);

        return mav;

    }

    @PostMapping
    public String writeBbs(@RequestBody Bbs bbs){
        Bbs newBbs = this.bbsService.writeBbs(bbs);

        return "redirect:/bbs/" + newBbs.getId();

    }


    @PutMapping("/{bbsId}")
    public String editBbs(@PathVariable("bbsId") Long bbsId, @RequestBody Bbs bbs){
        if(!bbs.getId().equals(bbsId)){

        }else{
            this.bbsService.editBbs(bbs);
        }
        return "redirect:/bbs"+bbs.getId();
    }


    @DeleteMapping("/{bbsId}")
    public String deleteBbs(@PathVariable("bbsId") Long bbsId){

        this.bbsService.deleteBbs(bbsId);

        return "redirect:/bbs";
    }



}
