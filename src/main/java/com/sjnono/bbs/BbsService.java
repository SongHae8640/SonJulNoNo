package com.sjnono.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BbsService {

    @Autowired
    BbsRepository bbsRepository;

    public List<Bbs> showBbsAll(String title) {
        List<Bbs> bbsList = this.bbsRepository.findAll();

        return bbsList;
    }
    @Transactional
    public Bbs findById(Long bbsId) {
        Bbs bbs = this.bbsRepository.findById(bbsId).get();

        //조회수 +1
        bbs.setHits(bbs.getHits()+1);

        return bbs;
    }

    public Bbs writeBbs(Bbs bbs) {
        this.bbsRepository.save(bbs);

        return bbs;
    }


    public void deleteBbs(Long bbsId){
        this.bbsRepository.deleteById(bbsId);
    }

    public void editBbs(Bbs bbs) {
        Bbs findBbs = this.bbsRepository.findById(bbs.getId()).get();
        bbs.setUserInfo(findBbs.getUserInfo());

    }
}
