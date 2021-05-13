package com.sjnono.bbs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sjnono.user.QUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.sjnono.bbs.QBbs.*;

@RequiredArgsConstructor
public class BbsRepositoryCustomImpl implements BbsRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Bbs findByIdQueryDsl(Long id){
        return queryFactory
                .selectFrom(bbs)
                .join(bbs.userInfo, QUserInfo.userInfo)
                .where(bbs.id.eq(id))
                .fetchOne();
    }

}
