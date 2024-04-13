package com.rainyheaven.modooreport.core.db.domain.member.follow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rainyheaven.modooreport.core.db.domain.member.QMember.member;
import static com.rainyheaven.modooreport.core.db.domain.member.follow.QMemberFollow.memberFollow;

@Repository
@RequiredArgsConstructor
public class MemberFollowQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<MemberFollow> findPageByFollower(final long followerId, final Pageable pageable) {
        List<MemberFollow> list = jpaQueryFactory.select(memberFollow)
                .from(memberFollow)
                .join(memberFollow.leader, member).fetchJoin()
                .where(
                        memberFollow.follower.id.eq(followerId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = jpaQueryFactory.select(memberFollow.count())
                .from(memberFollow)
                .where(
                        memberFollow.follower.id.eq(followerId)
                ).fetchOne();
        
        return new PageImpl<>(list, pageable, count);
    }
}
