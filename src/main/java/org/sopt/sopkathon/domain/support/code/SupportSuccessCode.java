package org.sopt.sopkathon.domain.support.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SupportSuccessCode implements SuccessCode {

    SUPPORT_SUCCEED(HttpStatus.CREATED, "게시글 공감에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}