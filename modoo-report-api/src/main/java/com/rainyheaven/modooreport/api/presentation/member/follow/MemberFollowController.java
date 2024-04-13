package com.rainyheaven.modooreport.api.presentation.member.follow;

import com.rainyheaven.modooreport.api.application.config.jwt.MemberToken;
import com.rainyheaven.modooreport.api.application.domain.member.MemberFollowService;
import com.rainyheaven.modooreport.api.presentation.member.follow.dto.MemberFollowRequest;
import com.rainyheaven.modooreport.api.presentation.member.follow.dto.MemberFollowResponse;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "팔로우")
@RestController
@RequestMapping("/api/v1/member-follows")
@RequiredArgsConstructor
public class MemberFollowController {

    private final MemberFollowService memberFollowService;

    @Operation(summary = "팔로우", description = "팔로우")
    @PostMapping
    public ResponseEntity<MemberFollowResponse> follow(
            @RequestBody final MemberFollowRequest request,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        MemberFollow follow = memberFollowService.follow(request.getLeaderId(), memberToken.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MemberFollowResponse.create(follow));
    }

    @Operation(summary = "언팔로우", description = "언팔로우")
    @DeleteMapping
    public void unfollow(
            @RequestBody final MemberFollowRequest request,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        memberFollowService.unfollow(request.getLeaderId(), memberToken.getId());
    }

}
