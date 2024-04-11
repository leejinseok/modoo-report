package com.rainyheaven.modooreport.core.db.domain.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rainyheaven.modooreport.core.db.domain.member.QMember.member;
import static com.rainyheaven.modooreport.core.db.domain.report.QReport.report;

@Repository
@RequiredArgsConstructor
public class ReportQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Report> findPage(final String query, final Pageable pageable) {
        List<Report> list = jpaQueryFactory
                .select(report)
                .from(report)
                .join(report.author, member).fetchJoin()
                .where(
                        report.title.contains(query),
                        report.content.contains(query)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = jpaQueryFactory.select(report.count())
                .from(report)
                .where(
                        report.title.contains(query),
                        report.content.contains(query)
                )
                .fetchOne();

        return new PageImpl<>(list, pageable, count);
    }
}
