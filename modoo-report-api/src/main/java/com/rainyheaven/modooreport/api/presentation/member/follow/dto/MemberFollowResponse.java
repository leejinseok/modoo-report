package com.rainyheaven.modooreport.api.presentation.member.follow.dto;

import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class MemberFollowResponse {

    private MemberResponse leader;
    private MemberResponse follower;
    private LocalDateTime createdDateTime;

    public static MemberFollowResponse create(final MemberFollow memberFollow) {
        MemberResponse leader = MemberResponse.create(memberFollow.getLeader());
        MemberResponse follower = MemberResponse.create(memberFollow.getFollower());
        return of(
                leader, follower, memberFollow.getCreatedDateTime()
        );
    }
}
