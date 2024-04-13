package com.rainyheaven.modooreport.api.presentation.member.like.report;

import com.rainyheaven.modooreport.api.application.config.jwt.MemberToken;
import com.rainyheaven.modooreport.api.application.domain.member.like.report.MemberLikeReportService;
import com.rainyheaven.modooreport.api.presentation.member.like.report.dto.MemberLikeReportRequest;
import com.rainyheaven.modooreport.api.presentation.member.like.report.dto.MemberLikeReportResponse;
import com.rainyheaven.modooreport.core.db.domain.member.like.report.MemberLikeReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요 (레포트)")
@RestController
@RequestMapping("/api/v1/member-like-reports")
@RequiredArgsConstructor
public class MemberLikeReportController {

    private final MemberLikeReportService memberLikeReportService;

    @Operation(summary = "좋아요 남기기", description = "좋아요 남기기")
    @PostMapping
    public ResponseEntity<MemberLikeReportResponse> likeReport(
            @RequestBody final MemberLikeReportRequest request,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        MemberLikeReport memberLikeReport = memberLikeReportService.likeReport(request.getReportId(), memberToken.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MemberLikeReportResponse.create(memberLikeReport));
    }


    @Operation(summary = "좋아요 취소", description = "좋아요 취소")
    @DeleteMapping("/{memberLikeReportId}")
    public void delete(
            @PathVariable final long memberLikeReportId,
            @AuthenticationPrincipal final MemberToken memberToken
    ) {
        memberLikeReportService.cancelLikeReport(memberLikeReportId, memberToken.getId());
    }


}
