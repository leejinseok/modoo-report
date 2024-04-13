package com.rainyheaven.modooreport.api.presentation.member.like.report.dto;

import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import com.rainyheaven.modooreport.api.presentation.report.dto.ReportResponse;
import com.rainyheaven.modooreport.core.db.domain.member.like.report.MemberLikeReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class MemberLikeReportResponse {

    private ReportResponse likedReport;
    private MemberResponse member;

    public static MemberLikeReportResponse create(final MemberLikeReport memberLikeReport) {
        return of(
                ReportResponse.createSimple(memberLikeReport.getReport()),
                MemberResponse.createSimple(memberLikeReport.getMember())
        );
    }

}
