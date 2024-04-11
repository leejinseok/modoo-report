package com.rainyheaven.modooreport.api.presentation.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class SignUpRequest {

    @Schema(example = "김주식")
    private String name;

    @Schema(example = "sample@sample.com")
    private String email;

    @Schema(example = "주식고수")
    private String nickname;

    @Schema(example = "password")
    private String password;


}
