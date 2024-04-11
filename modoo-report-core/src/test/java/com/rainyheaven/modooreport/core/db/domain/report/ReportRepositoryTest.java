package com.rainyheaven.modooreport.core.db.domain.report;

import com.rainyheaven.modooreport.core.CoreTestConfiguration;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberFactory;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = CoreTestConfiguration.class)
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member author = memberRepository.save(MemberFactory.sampleMember(null));
        Report report = Report.builder()
                .author(author)
                .title("나의 견해")
                .content("이 주식을 사라니 마라니")
                .recommended(Recommended.STRONG_BUY)
                .targetPrice(new BigDecimal(10000))
                .build();

        reportRepository.save(report);
    }

}