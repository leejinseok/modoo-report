package com.rainyheaven.modooreport.core.db.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainyheaven.modooreport.core.db.domain.member.follow.MemberFollowQueryDslRepository;
import com.rainyheaven.modooreport.core.db.domain.report.ReportQueryDslRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReportQueryDslRepository.class, MemberFollowQueryDslRepository.class})
public class CoreQueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
