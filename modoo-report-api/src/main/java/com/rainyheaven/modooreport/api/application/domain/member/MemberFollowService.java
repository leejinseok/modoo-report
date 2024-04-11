package com.rainyheaven.modooreport.api.application.domain.member;

import com.rainyheaven.modooreport.api.exception.DuplicatedException;
import com.rainyheaven.modooreport.api.exception.ExceptionConstants;
import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFollowService {

    private final MemberRepository memberRepository;
    private final MemberFollowRepository memberFollowRepository;

    @Transactional
    public MemberFollow follow(final long leaderId, final long followerId) {
        Member leader = memberRepository
                .findById(leaderId)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND_MEMBER_MESSAGE.formatted(leaderId)));

        Member follower = memberRepository
                .findById(followerId)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND_MEMBER_MESSAGE.formatted(followerId)));


        memberFollowRepository.findByLeaderIdAndFollowerId(leaderId, followerId).ifPresent(memberFollow -> {
            throw new DuplicatedException("이미 팔로우한 사용자입니다.");
        });

        MemberFollow follow = MemberFollow.builder()
                .follower(follower)
                .leader(leader)
                .build();


        return memberFollowRepository.save(follow);
    }
}
