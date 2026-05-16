package org.sopt.sopkathon.domain.comment.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    COMMENT_LIKE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 좋아요한 댓글입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}