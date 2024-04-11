package com.rainyheaven.modooreport.api.presentation.report.dto;

import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import com.rainyheaven.modooreport.core.db.domain.report.Recommended;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "of")
public class ReportResponse {

    private Long id;
    private String title;
    private String content;
    private BigDecimal targetPrice;
    private Recommended recommended;
    private MemberResponse author;

    public static ReportResponse create(final Report report) {
        return ReportResponse.of(
                report.getId(),
                report.getTitle(),
                report.getContent(),
                report.getTargetPrice(),
                report.getRecommended(),
                MemberResponse.create(report.getAuthor())
        );
    }
}
