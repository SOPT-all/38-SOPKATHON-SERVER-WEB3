package org.sopt.sopkathon.domain.clap.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.clap.code.ClapSuccessCode;
import org.sopt.sopkathon.domain.clap.service.ClapService;
import org.sopt.sopkathon.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Clap", description = "박수 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ClapController {

    private final ClapService clapService;

    @Operation(summary = "박수 누르기", description = "게시글에 박수를 누릅니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "박수 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 멤버 없음"),
            @ApiResponse(responseCode = "409", description = "이미 박수를 누른 게시글")
    })
    @PostMapping("/{postId}/clap")
    public ResponseEntity<BaseResponse<Void>> clap(
            @RequestHeader("Member-Id") Long memberId,
            @PathVariable Long postId
    ) {
        clapService.clap(memberId, postId);
        return ResponseEntity
                .status(ClapSuccessCode.CLAP_SUCCEED.getHttpStatus())
                .body(BaseResponse.success(ClapSuccessCode.CLAP_SUCCEED));
    }
}