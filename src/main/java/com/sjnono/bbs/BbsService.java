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


    public List<Bbs> showBbsAll() {
        List<Bbs> bbsList = this.bbsRepository.findAll();

        return bbsList;
    }


    @Transactional
    public Bbs findByIdFetchJoin(Long bbsId){
        Bbs bbs = this.bbsRepository.findByIdFetchJoin(bbsId);

        //조회수 +1
        bbs.setHits(bbs.getHits()+1);

        return bbs;
    }


    @Transactional
    public Bbs writeBbs(Bbs bbs) {
        this.bbsRepository.save(bbs);
        bbs.getUserInfo();

        return bbs;
    }


    public void deleteBbs(Long bbsId){

        this.bbsRepository.deleteById(bbsId);
    }

    @Transactional
    public Bbs editBbs(Long bbsId, Bbs bbs) {
        Bbs editedBbs = this.bbsRepository.findById(bbsId).get();
        editedBbs.setTitle(bbs.getTitle());
        editedBbs.setContent(bbs.getContent());

        return editedBbs;
    }
}
