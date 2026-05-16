package org.sopt.sopkathon.domain.clap.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClapErrorCode implements ErrorCode {

    CLAP_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 박수를 누른 게시글입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}