package org.sopt.sopkathon.domain.comment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentSuccessCode implements SuccessCode {

    COMMENT_CREATE_SUCCEED(HttpStatus.CREATED, "댓글이 성공적으로 생성되었습니다."),
    COMMENT_LIKE_SUCCEED(HttpStatus.CREATED, "댓글 좋아요에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}