package com.rainyheaven.modooreport.api.presentation.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal targetPrice;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static ReportResponse createSimple(final Report report) {
        return of(
                report.getId(),
                report.getTitle(),
                null,
                null,
                null,
                MemberResponse.createSimple(report.getAuthor())
        );
    }
}
