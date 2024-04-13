package com.rainyheaven.modooreport.api.application.domain.member.like.report;

import com.rainyheaven.modooreport.api.exception.ExceptionConstants;
import com.rainyheaven.modooreport.api.exception.NoPermissionException;
import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import com.rainyheaven.modooreport.core.db.domain.member.like.report.MemberLikeReport;
import com.rainyheaven.modooreport.core.db.domain.member.like.report.MemberLikeReportRepository;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import com.rainyheaven.modooreport.core.db.domain.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberLikeReportService {

    private final MemberLikeReportRepository memberLikeReportRepository;
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberLikeReport likeReport(final long reportId, final long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND_MEMBER_MESSAGE.formatted(memberId)));

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND_REPORT_MESSAGE.formatted(reportId)));

        MemberLikeReport memberLikeReport = MemberLikeReport.builder()
                .report(report)
                .member(member)
                .build();

        return memberLikeReportRepository.save(memberLikeReport);
    }

    @Transactional
    public void cancelLikeReport(final long memberLikeReportId, final Long memberId) {
        MemberLikeReport memberLikeReport = memberLikeReportRepository.findById(memberLikeReportId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 정보입니다."));

        Member member = memberLikeReport.getMember();
        if (!member.getId().equals(memberId)) {
            throw new NoPermissionException("해당 좋아요를 삭제할 권한이 없습니다.");
        }

        memberLikeReportRepository.delete(memberLikeReport);
    }

}
