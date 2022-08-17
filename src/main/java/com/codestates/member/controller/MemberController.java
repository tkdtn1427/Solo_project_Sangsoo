package com.codestates.member.controller;

import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {
    private MemberService memberService;
    private MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper){
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public ResponseEntity getMembers(){
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> response = memberMapper.membersToMemberResponses(members);

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity getMembersByLocation(@PathVariable("location") String location){
        List<Member> members = memberService.findMembersBylocation(location);
        List<MemberResponseDto> response = memberMapper.membersToMemberResponses(members);

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getMembersByType(@PathVariable("type") String type){
        List<Member> members = memberService.findMembersByType(type);
        List<MemberResponseDto> response = memberMapper.membersToMemberResponses(members);

        return new ResponseEntity(response,HttpStatus.OK);
    }
}
