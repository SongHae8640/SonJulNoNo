package com.sjnono.bbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BbsRepository extends JpaRepository<Bbs, Long>{

    List<Bbs> findByTitle(String title);
}
