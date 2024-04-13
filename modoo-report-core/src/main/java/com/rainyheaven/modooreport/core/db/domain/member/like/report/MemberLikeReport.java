package com.rainyheaven.modooreport.core.db.domain.member.like.report;

import com.rainyheaven.modooreport.core.db.domain.common.AuditingDomain;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.report.Report;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_like_report", uniqueConstraints = {
        @UniqueConstraint(name = "uk_member_like_report_1", columnNames = {"member_id", "report_id"})
})
public class MemberLikeReport extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_like_report_1"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", foreignKey = @ForeignKey(name = "fk_member_like_report_@"))
    private Report report;

}
