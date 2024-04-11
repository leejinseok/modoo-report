package com.rainyheaven.modooreport.api.application.domain.report;

import com.rainyheaven.modooreport.api.application.config.ApiDbConfig;
import com.rainyheaven.modooreport.api.application.domain.member.MemberFactory;
import com.rainyheaven.modooreport.api.presentation.report.dto.ReportRequestFactory;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
@DataJpaTest
@Import({ApiDbConfig.class, ReportService.class})
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member member = MemberFactory.sampleMember(null);
        memberRepository.save(member);
        reportService.save(ReportRequestFactory.sampleReportRequest(), member.getId());
    }

    @AfterEach
    void teardown() {
        memberRepository.deleteAll();
    }

}