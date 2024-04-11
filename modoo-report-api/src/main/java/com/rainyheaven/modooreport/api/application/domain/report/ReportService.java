package com.rainyheaven.modooreport.api.application.domain.report;

import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.api.presentation.report.dto.ReportRequest;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import com.rainyheaven.modooreport.core.db.domain.report.ReportQueryDslRepository;
import com.rainyheaven.modooreport.core.db.domain.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rainyheaven.modooreport.api.exception.ExceptionConstants.NOT_FOUND_MEMBER_MESSAGE;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportQueryDslRepository reportQueryDslRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<Report> findPage(final String query, final Pageable pageable) {
        return reportQueryDslRepository.findPage(query, pageable);
    }

    @Transactional
    public Report save(final ReportRequest request, final Long authorId) {
        Member member = memberRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER_MESSAGE.formatted(authorId)));

        Report report = Report.builder()
                .targetPrice(request.getTargetPrice())
                .title(request.getTitle())
                .content(request.getContent())
                .author(member)
                .recommended(request.getRecommended())
                .build();

        return reportRepository.save(report);
    }

}
