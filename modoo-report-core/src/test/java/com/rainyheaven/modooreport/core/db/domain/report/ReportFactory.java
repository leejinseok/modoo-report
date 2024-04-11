package com.rainyheaven.modooreport.core.db.domain.report;

import com.rainyheaven.modooreport.core.db.domain.member.Member;

import java.math.BigDecimal;

import static com.rainyheaven.modooreport.core.RandomStringUtils.generateRandomString;

public class ReportFactory {

    public static Report randomReport(final Member member) {
        return Report.builder()
                .author(member)
                .title(generateRandomString(20))
                .content(generateRandomString(50))
                .recommended(Recommended.STRONG_BUY)
                .targetPrice(new BigDecimal(1000))
                .build();
    }
}
