package com.rainyheaven.modooreport.api.presentation.auth.dto;

import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class LoginResponse {

    private MemberResponse member;
    private TokenResponse token;

}
