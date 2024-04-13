package com.rainyheaven.modooreport.api.application.domain.member;

import com.rainyheaven.modooreport.api.exception.DuplicatedException;
import com.rainyheaven.modooreport.api.exception.ExceptionConstants;
import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollowQueryDslRepository;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFollowService {

    private final MemberRepository memberRepository;
    private final MemberFollowRepository memberFollowRepository;
    private final MemberFollowQueryDslRepository memberFollowQueryDslRepository;

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

    @Transactional(readOnly = true)
    public Page<MemberFollow> findPage(final long followerId, final Pageable pageable) {
        return memberFollowQueryDslRepository.findPageByFollower(followerId, pageable);
    }

    @Transactional
    public void unfollow(final long leaderId, final Long followerId) {
        MemberFollow memberFollow = memberFollowRepository.findByLeaderIdAndFollowerId(leaderId, followerId)
                .orElseThrow(() -> new NotFoundException("해당 사용자를 팔로우하고 있지 않습니다."));
        memberFollowRepository.delete(memberFollow);
    }
}
