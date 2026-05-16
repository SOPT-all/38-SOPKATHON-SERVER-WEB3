package org.sopt.sopkathon.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.member.dto.response.MemberPageResponse;
import org.sopt.sopkathon.domain.member.service.MemberService;
import org.sopt.sopkathon.domain.post.code.PostSuccessCode;
import org.sopt.sopkathon.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "myPage", description = "마이페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;


    @Operation(summary = "마이페이지 조회 ", description = "마이페이지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재 하지 않는 멤버"),
            @ApiResponse(responseCode = "404", description = "존재 하지 않는 게시글"),
    })
    @GetMapping
    public ResponseEntity<BaseResponse<MemberPageResponse>> getPosts(
            @RequestHeader("Member-Id") Long memberId
    ) {

        MemberPageResponse response = memberService.findMyPage(memberId);

        return ResponseEntity
                .status(PostSuccessCode.FIND_POST_LIST.getHttpStatus())
                .body(BaseResponse.success(PostSuccessCode.FIND_POST_LIST, response));
    }
}