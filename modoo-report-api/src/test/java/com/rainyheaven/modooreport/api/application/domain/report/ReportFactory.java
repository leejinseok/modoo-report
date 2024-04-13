package com.rainyheaven.modooreport.api.application.domain.report;

import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.report.Recommended;
import com.rainyheaven.modooreport.core.db.domain.report.Report;

import java.math.BigDecimal;

public class ReportFactory {

    public static Report sampleReport(final Member member) {
        return Report.builder()
                .author(member)
                .title("title")
                .content("content")
                .recommended(Recommended.STRONG_BUY)
                .targetPrice(new BigDecimal(1000))
                .build();
    }

}
