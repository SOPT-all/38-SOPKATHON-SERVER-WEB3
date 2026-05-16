package org.sopt.sopkathon.domain.support.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.support.code.SupportSuccessCode;
import org.sopt.sopkathon.domain.support.service.SupportService;
import org.sopt.sopkathon.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Support", description = "공감 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class SupportController {

    private final SupportService supportService;

    @Operation(summary = "공감 누르기", description = "게시글에 공감을 누릅니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공감 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 멤버 없음"),
            @ApiResponse(responseCode = "409", description = "이미 공감을 누른 게시글")
    })
    @PostMapping("/{postId}/support")
    public ResponseEntity<BaseResponse<Void>> support(
            @RequestHeader("Member-Id") Long memberId,
            @PathVariable Long postId
    ) {
        supportService.support(memberId, postId);
        return ResponseEntity
                .status(SupportSuccessCode.SUPPORT_SUCCEED.getHttpStatus())
                .body(BaseResponse.success(SupportSuccessCode.SUPPORT_SUCCEED));
    }
}