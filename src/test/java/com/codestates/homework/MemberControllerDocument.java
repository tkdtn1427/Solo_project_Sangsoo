package com.codestates.homework;

import com.codestates.member.controller.MemberController;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static com.codestates.util.ApiDocument.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerDocument {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Autowired
    private Gson gson;

    @Test
    public void getmembersTest() throws Exception{

        List<MemberResponseDto> result = new ArrayList<>(List.of(
                new MemberResponseDto(1L,"?????????","m","????????????????????????",005,001),
                new MemberResponseDto(2L,"?????????","w","????????????????????????",003,002)
        ));

        given(memberService.findMembers()).willReturn(new ArrayList<>());
        given(memberMapper.membersToMemberResponses(Mockito.any(List.class))).willReturn(result);

        ResultActions actions = mockMvc.perform(
                get("/v1/members")
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-members",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].sex").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_type").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                        fieldWithPath("[].company_location").type(JsonFieldType.NUMBER).description("?????? ?????? ??????")
                                )
                        )
                ));

    }

    @Test
    public void getmembers_LocationTest() throws Exception{
        int location = 001;

        List<MemberResponseDto> result = new ArrayList<>(List.of(
                new MemberResponseDto(1L,"?????????","m","????????????????????????",005,001),
                new MemberResponseDto(2L,"?????????","w","????????????????????????",003,001)
        ));

        given(memberService.findMembers()).willReturn(new ArrayList<>());
        given(memberMapper.membersToMemberResponses(Mockito.any(List.class))).willReturn(result);

        ResultActions actions = mockMvc.perform(
                get("/v1/members/location/{location}",location)
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-membersByLocation",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("location").description("?????? ?????? ??????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].sex").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_type").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                        fieldWithPath("[].company_location").type(JsonFieldType.NUMBER).description("?????? ?????? ??????")
                                )
                        )
                ));

    }

    @Test
    public void getmembers_TypeTest() throws Exception{
        int type = 005;

        List<MemberResponseDto> result = new ArrayList<>(List.of(
                new MemberResponseDto(1L,"?????????","m","????????????????????????",005,001),
                new MemberResponseDto(2L,"?????????","w","????????????????????????",005,002)
        ));

        given(memberService.findMembers()).willReturn(new ArrayList<>());
        given(memberMapper.membersToMemberResponses(Mockito.any(List.class))).willReturn(result);

        ResultActions actions = mockMvc.perform(
                get("/v1/members/type/{type}",type)
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-membersByType",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("type").description("?????? ?????? ??????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].sex").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("[].company_type").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                        fieldWithPath("[].company_location").type(JsonFieldType.NUMBER).description("?????? ?????? ??????")
                                )
                        )
                ));

    }
}
