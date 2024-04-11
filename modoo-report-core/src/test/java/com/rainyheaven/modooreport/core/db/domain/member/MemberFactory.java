package com.rainyheaven.modooreport.core.db.domain.member;

import com.rainyheaven.modooreport.core.db.domain.common.Phone;

public class MemberFactory {

    public static Member sampleMember(Long id) {
        Phone phone = new Phone("010", "1111", "2222");
        return Member.builder()
                .id(id)
                .email("sample@sample.com")
                .phone(phone)
                .name("김주식")
                .nickname("주식고수")
                .password("password")
                .build();
    }
}
