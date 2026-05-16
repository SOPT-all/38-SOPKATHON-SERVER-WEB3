package org.sopt.sopkathon.domain.support.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SupportErrorCode implements ErrorCode {

    SUPPORT_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 공감을 누른 게시글입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}