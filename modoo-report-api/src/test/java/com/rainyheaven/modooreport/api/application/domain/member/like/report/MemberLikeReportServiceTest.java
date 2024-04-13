package com.rainyheaven.modooreport.api.application.domain.member.like.report;

import com.rainyheaven.modooreport.api.application.config.ApiDbConfig;
import com.rainyheaven.modooreport.api.application.domain.member.MemberFactory;
import com.rainyheaven.modooreport.api.application.domain.report.ReportFactory;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.member.like.report.MemberLikeReport;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import com.rainyheaven.modooreport.core.db.domain.report.ReportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@DataJpaTest
@Import({ApiDbConfig.class, MemberLikeReportService.class})
class MemberLikeReportServiceTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberLikeReportService memberLikeReportService;

    @DisplayName("레포트 좋아요, 좋아요 취소")
    @Test
    void likeReportAndCancelLikeReport() {
        Member member = MemberFactory.sampleMember(null);
        memberRepository.save(member);
        Report report = reportRepository.save(ReportFactory.sampleReport(member));
        MemberLikeReport memberLikeReport = memberLikeReportService.likeReport(report.getId(), member.getId());

        assertThat(memberLikeReport.getMember().getId()).isEqualTo(member.getId());
        assertThat(memberLikeReport.getReport().getId()).isEqualTo(report.getId());

        memberLikeReportService.cancelLikeReport(memberLikeReport.getId(), member.getId());
    }

}