package org.sopt.sopkathon.domain.post.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    INVALID_POST_FILTER(HttpStatus.BAD_GATEWAY, "존재 하지 않는 필터입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}