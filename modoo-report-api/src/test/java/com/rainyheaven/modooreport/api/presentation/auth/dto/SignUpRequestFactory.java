package com.rainyheaven.modooreport.api.presentation.auth.dto;

public class SignUpRequestFactory {

    public static SignUpRequest createSampleSignUpRequest() {
        return SignUpRequest.of(
                "김주식",
                "sample@sample.com",
                "주식고수",
                "password"
        );
    }

}