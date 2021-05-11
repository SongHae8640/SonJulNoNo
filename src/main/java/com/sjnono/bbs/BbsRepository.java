package com.sjnono.bbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BbsRepository extends JpaRepository<Bbs, Long>{

    List<Bbs> findByTitle(String title);

    @Query("select b from BOARD b join fetch b.userInfo where b.id = :#{#bbsId}" )
    Bbs findByIdFetchJoin(Long bbsId);
}
