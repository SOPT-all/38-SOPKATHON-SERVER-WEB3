package org.sopt.sopkathon.domain.clap.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClapSuccessCode implements SuccessCode {

    CLAP_SUCCEED(HttpStatus.CREATED, "게시글 박수에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}