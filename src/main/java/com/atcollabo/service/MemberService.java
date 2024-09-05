package com.atcollabo.service;

import com.atcollabo.entity.Member;
import com.atcollabo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private final PasswordEncoder passEncoder;


    public Member registerNewMember(Member registration) {
        Member member = new Member();
        member.setMbrId(registration.getMbrId());
        member.setMbrPw(passEncoder.encode(registration.getMbrPw()));

        return memberRepository.save(member);
    }
}