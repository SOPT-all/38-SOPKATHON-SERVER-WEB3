package org.sopt.sopkathon.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.comment.code.CommentSuccessCode;
import org.sopt.sopkathon.domain.comment.dto.request.CreateCommentRequest;
import org.sopt.sopkathon.domain.comment.dto.response.CreateCommentResponse;
import org.sopt.sopkathon.domain.comment.service.CommentService;
import org.sopt.sopkathon.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment", description = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "게시글에 댓글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "댓글 생성 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 멤버 없음")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CreateCommentResponse>> createComment(
            @RequestHeader("Member-Id") Long memberId,
            @PathVariable Long postId,
            @RequestBody CreateCommentRequest request
    ) {
        CreateCommentResponse response = commentService.createComment(memberId, postId, request);
        return ResponseEntity
                .status(CommentSuccessCode.COMMENT_CREATE_SUCCEED.getHttpStatus())
                .body(BaseResponse.success(CommentSuccessCode.COMMENT_CREATE_SUCCEED, response));
    }

    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 누릅니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "댓글 좋아요 성공"),
            @ApiResponse(responseCode = "404", description = "게시글, 댓글 또는 멤버 없음"),
            @ApiResponse(responseCode = "409", description = "이미 좋아요한 댓글")
    })
    @PostMapping("/{commentId}/like")
    public ResponseEntity<BaseResponse<Void>> likeComment(
            @RequestHeader("Member-Id") Long memberId,
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.likeComment(memberId, postId, commentId);
        return ResponseEntity
                .status(CommentSuccessCode.COMMENT_LIKE_SUCCEED.getHttpStatus())
                .body(BaseResponse.success(CommentSuccessCode.COMMENT_LIKE_SUCCEED));
    }
}