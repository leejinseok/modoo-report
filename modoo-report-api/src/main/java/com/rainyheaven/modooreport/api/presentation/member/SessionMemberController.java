package com.rainyheaven.modooreport.api.presentation.member;

import com.rainyheaven.modooreport.api.application.config.jwt.MemberToken;
import com.rainyheaven.modooreport.api.application.domain.member.MemberFollowService;
import com.rainyheaven.modooreport.api.application.domain.member.MemberService;
import com.rainyheaven.modooreport.api.presentation.common.dto.PageResponse;
import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import com.rainyheaven.modooreport.api.presentation.member.follow.dto.MemberFollowResponse;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인 유저")
@RestController
@RequestMapping("/api/v1/session-member")
@RequiredArgsConstructor
public class SessionMemberController {

    private final MemberFollowService memberFollowService;
    private final MemberService memberService;

    @Operation(summary = "내정보", description = "내정보")
    @GetMapping
    public ResponseEntity<MemberResponse> getMe(@AuthenticationPrincipal final MemberToken memberToken) {
        Member member = memberService.findById(memberToken.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MemberResponse.create(member));
    }

    @Operation(summary = "팔로우 정보", description = "팔로우 정보")
    @GetMapping("/follows")
    public ResponseEntity<PageResponse<MemberFollowResponse>> getPage(
            @Parameter(name = "pageNo") final int pageNo,
            @Parameter(name = "pageSize") final int pageSize,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        Page<MemberFollow> page = memberFollowService.findPage(memberToken.getId(), PageRequest.of(pageNo, pageSize));
        Page<MemberFollowResponse> map = page.map(MemberFollowResponse::create);
        PageResponse<MemberFollowResponse> body = new PageResponse<>(map);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

}
