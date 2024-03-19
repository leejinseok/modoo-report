package com.rainyheaven.modooreport.core.db.domain.member.follow;

import com.rainyheaven.modooreport.core.db.domain.common.AuditingDomain;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
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
@Table(name = "member_follow", uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_idx_member_follow_1",
                columnNames = {"follower_id", "leader_id"}
        )
})
public class MemberFollow extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "follower_id",
            foreignKey = @ForeignKey(name = "fk_member_follow_1"),
            nullable = false
    )
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "leader_id",
            foreignKey = @ForeignKey(name = "fk_member_follow_2"),
            nullable = false
    )
    private Member leader;

}
