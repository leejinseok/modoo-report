package com.rainyheaven.modooreport.api.presentation.report.dto;

import com.rainyheaven.modooreport.core.db.domain.report.Recommended;

import java.math.BigDecimal;

public class ReportRequestFactory {

    public static ReportRequest sampleReportRequest() {
        return ReportRequest.of(
                "이 주식 대박!",
                "이 주식은 대박이 날것이다.",
                new BigDecimal(12000),
                Recommended.STRONG_BUY
        );
    }
}
