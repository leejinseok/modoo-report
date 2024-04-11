package com.rainyheaven.modooreport.api.presentation.auth.dto;

import lombok.Getter;

@Getter
public class RefreshTokenRequest {

    private String accessToken;
    private String refreshToken;

}
