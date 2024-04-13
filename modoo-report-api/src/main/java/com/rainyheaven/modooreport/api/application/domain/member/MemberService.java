package com.rainyheaven.modooreport.api.application.domain.member;

import com.rainyheaven.modooreport.api.exception.ExceptionConstants;
import com.rainyheaven.modooreport.api.exception.NotFoundException;
import com.rainyheaven.modooreport.core.db.domain.member.Member;
import com.rainyheaven.modooreport.core.db.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(final long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND_MEMBER_MESSAGE.formatted(id)));
    }

}
