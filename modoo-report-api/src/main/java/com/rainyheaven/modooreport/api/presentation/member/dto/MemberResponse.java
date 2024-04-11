package com.rainyheaven.modooreport.api.presentation.member.dto;

import com.rainyheaven.modooreport.api.application.util.AES256Util;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class MemberResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "김주식")
    private String name;

    @Schema(example = "sample@sample.com")
    private String email;

    private LocalDateTime createdDateTime;

    public static MemberResponse create(final Member member) {
        return MemberResponse.of(
                member.getId(),
                member.getName(),
                AES256Util.decrypt(member.getEmail()),
                member.getCreatedDateTime()
        );
    }

}
