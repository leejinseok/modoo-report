package com.rainyheaven.modooreport.api.application.domain.member;

import com.rainyheaven.modooreport.api.application.config.ApiDbConfig;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollowRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@DataJpaTest
@Import({ApiDbConfig.class, MemberFollowService.class})
class MemberFollowServiceTest {

    @Autowired
    private MemberFollowService memberFollowService;

    @Autowired
    private MemberFollowRepository memberFollowRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void teardown() {
        memberRepository.deleteAll();
        memberFollowRepository.deleteAll();
    }

    @DisplayName("팔로우")
    @Test
    void follow() {
        Member leader = Member.builder()
                .email("gosu@sample.com")
                .phone(null)
                .name("김주식")
                .nickname("주식고수")
                .password("password")
                .build();

        Member follower = Member.builder()
                .email("chobo@sample.com")
                .phone(null)
                .name("김초보")
                .nickname("주식초보")
                .password("password")
                .build();

        memberRepository.save(leader);
        memberRepository.save(follower);

        MemberFollow follow = memberFollowService.follow(leader.getId(), follower.getId());
        assertThat(follow.getFollower().getId()).isEqualTo(follower.getId());
        assertThat(follow.getLeader().getId()).isEqualTo(leader.getId());
    }

    @DisplayName("팔로우 내역")
    @Test
    void findPage() {
        Member leader = Member.builder()
                .email("gosu@sample.com")
                .phone(null)
                .name("김주식")
                .nickname("주식고수")
                .password("password")
                .build();

        Member follower = Member.builder()
                .email("chobo@sample.com")
                .phone(null)
                .name("김초보")
                .nickname("주식초보")
                .password("password")
                .build();
        memberRepository.save(leader);
        memberRepository.save(follower);


        memberFollowService.follow(leader.getId(), follower.getId());
        Page<MemberFollow> page = memberFollowService.findPage(follower.getId(), PageRequest.of(0, 10));

        MemberFollow first = page.getContent().getFirst();
        assertThat(first.getFollower().getId()).isEqualTo(follower.getId());
        assertThat(first.getLeader().getId()).isEqualTo(leader.getId());
    }

    @DisplayName("언팔로우")
    @Test
    void unfollow() {
        Member leader = Member.builder()
                .email("gosu@sample.com")
                .phone(null)
                .name("김주식")
                .nickname("주식고수")
                .password("password")
                .build();

        Member follower = Member.builder()
                .email("chobo@sample.com")
                .phone(null)
                .name("김초보")
                .nickname("주식초보")
                .password("password")
                .build();
        memberRepository.save(leader);
        memberRepository.save(follower);

        memberFollowService.follow(leader.getId(), follower.getId());
        memberFollowService.unfollow(leader.getId(), follower.getId());
    }


}