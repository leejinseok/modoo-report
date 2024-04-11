package com.rainyheaven.modooreport.api.application.domain.auth;

import com.rainyheaven.modooreport.api.application.config.ApiDbConfig;
import com.rainyheaven.modooreport.api.application.config.jwt.JwtConfig;
import com.rainyheaven.modooreport.api.application.config.jwt.JwtProvider;
import com.rainyheaven.modooreport.api.presentation.auth.dto.LoginRequest;
import com.rainyheaven.modooreport.api.presentation.auth.dto.LoginResponse;
import com.rainyheaven.modooreport.api.presentation.auth.dto.SignUpRequest;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.rainyheaven.modooreport.api.presentation.auth.dto.SignUpRequestFactory.createSampleSignUpRequest;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@DataJpaTest
@Import({ApiDbConfig.class, JwtConfig.class, AuthService.class, AuthServiceTestConfig.class})
class AuthServiceTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthService authService;

    @DisplayName("회원가입 테스트")
    @Test
    void signUp() {
        Member member = authService.signUp(SignUpRequest.of("김주식", "sample@sample.com", "주식고수", "password"));
    }

    @DisplayName("로그인")
    @Test
    void login() {
        SignUpRequest signUpRequest = createSampleSignUpRequest();
        authService.signUp(signUpRequest);

        LoginResponse login = authService.login(LoginRequest.of(signUpRequest.getEmail(), signUpRequest.getPassword()));
        assertThat(login).isNotNull();
        assertThat(login.getMember()).isNotNull();

        Jws<Claims> claims = jwtProvider.parse(login.getToken().getAccessToken());
        assertThat(claims.getBody().getSubject()).isEqualTo(signUpRequest.getEmail());
    }


}