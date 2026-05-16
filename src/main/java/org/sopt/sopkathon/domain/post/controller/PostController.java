package org.sopt.sopkathon.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.post.code.PostSuccessCode;
import org.sopt.sopkathon.domain.post.dto.request.CreatePostRequest;
import org.sopt.sopkathon.domain.post.dto.response.CreatePostResponse;
import org.sopt.sopkathon.domain.post.service.PostService;
import org.sopt.sopkathon.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post", description = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "게시글 생성 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 멤버")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CreatePostResponse>> createPost(
            @RequestHeader("Member-Id") Long memberId,
            @RequestBody CreatePostRequest request
    ) {
        CreatePostResponse response = postService.createPost(memberId, request);
        return ResponseEntity
                .status(PostSuccessCode.CREATE_POST.getHttpStatus())
                .body(BaseResponse.success(PostSuccessCode.CREATE_POST, response));
    }
}