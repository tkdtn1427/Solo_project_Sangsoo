package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberResponseDto {
    private Long memberId;

    private String name;

    private String sex;

    private String company_name;
    private int company_type;
    private int company_location;
}
