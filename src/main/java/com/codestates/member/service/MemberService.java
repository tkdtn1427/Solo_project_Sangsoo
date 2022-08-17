package com.codestates.member.service;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> findMembers(){
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public List<Member> findMembersBylocation(String location){
        Optional<List<Member>> optionalMember = memberRepository.findByCompany_Location(location);

        List<Member> members = optionalMember.orElseThrow();

        return members;
    }

    public List<Member> findMembersByType(String type){
        Optional<List<Member>> optionalMember = memberRepository.findByCompany_Type(type);
        List<Member> members = optionalMember.orElseThrow();

        return members;
    }
}
