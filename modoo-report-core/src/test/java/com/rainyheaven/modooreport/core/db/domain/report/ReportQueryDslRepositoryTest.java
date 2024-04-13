package com.rainyheaven.modooreport.core.db.domain.report;

import com.rainyheaven.modooreport.core.CoreTestConfiguration;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberFactory;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = CoreTestConfiguration.class)
class ReportQueryDslRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportQueryDslRepository reportQueryDslRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("레포트 검색")
    @Test
    void findPage() {
        Member member = MemberFactory.sampleMember(null);
        Member save = memberRepository.save(member);
        Report report = ReportFactory.randomReport(save);

        reportRepository.save(report);

        Page<Report> page = reportQueryDslRepository.findPage("", PageRequest.of(0, 10));
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().getFirst().getId()).isEqualTo(report.getId());
    }

}