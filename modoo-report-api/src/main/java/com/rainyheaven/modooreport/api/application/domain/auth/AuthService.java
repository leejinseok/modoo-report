package com.rainyheaven.modooreport.api.application.domain.auth;

import com.rainyheaven.modooreport.api.application.config.jwt.JwtProvider;
import com.rainyheaven.modooreport.api.application.util.AES256Util;
import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.api.exception.UnAuthorizedException;
import com.rainyheaven.modooreport.api.presentation.auth.dto.LoginRequest;
import com.rainyheaven.modooreport.api.presentation.auth.dto.LoginResponse;
import com.rainyheaven.modooreport.api.presentation.auth.dto.SignUpRequest;
import com.rainyheaven.modooreport.api.presentation.auth.dto.TokenResponse;
import com.rainyheaven.modooreport.api.presentation.member.dto.MemberResponse;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(final SignUpRequest signUpRequest) {

        String name = signUpRequest.getName();
        String nickname = signUpRequest.getNickname();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        String emailEncrypt = AES256Util.encrypt(email);
        String passwordEncoded = passwordEncoder.encode(password);

        Member member = Member.builder()
                .name(name)
                .nickname(nickname)
                .email(emailEncrypt)
                .password(passwordEncoded)
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(final LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String decrypt = AES256Util.encrypt(email);
        Member member = memberRepository.findByEmail(decrypt).orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));

        String password = member.getPassword();
        if (!passwordEncoder.matches(loginRequest.getPassword(), password)) {
            throw new UnAuthorizedException("패스워드가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.createToken(member);
        String refreshToken = jwtProvider.createRefreshToken(member);

        MemberResponse memberResponse = MemberResponse.of(
                member.getId(), member.getName(), email, member.getCreatedDateTime()
        );
        TokenResponse tokenResponse = TokenResponse.of(accessToken, refreshToken);

        return LoginResponse.of(memberResponse, tokenResponse);
    }

}
