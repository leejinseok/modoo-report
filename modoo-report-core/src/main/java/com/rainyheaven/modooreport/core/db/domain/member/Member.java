package com.rainyheaven.modooreport.core.db.domain.member;

import com.rainyheaven.modooreport.core.db.domain.common.AuditingDomain;
import com.rainyheaven.modooreport.core.db.domain.common.Phone;
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
@Table(name = "member")
public class Member extends AuditingDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String name;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Phone phone;



}
