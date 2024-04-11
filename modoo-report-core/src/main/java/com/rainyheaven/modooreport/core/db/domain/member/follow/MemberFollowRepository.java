package com.rainyheaven.modooreport.core.db.domain.member.follow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long> {

    Optional<MemberFollow> findByLeaderIdAndFollowerId(long leaderId, long followerId);
}
