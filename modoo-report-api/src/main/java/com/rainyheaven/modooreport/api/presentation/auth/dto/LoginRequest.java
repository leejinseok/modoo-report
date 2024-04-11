package com.rainyheaven.modooreport.api.presentation.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class LoginRequest {

    @Schema(example = "sample@sample.com")
    private final String email;

    @Schema(example = "password")
    private final String password;

}
