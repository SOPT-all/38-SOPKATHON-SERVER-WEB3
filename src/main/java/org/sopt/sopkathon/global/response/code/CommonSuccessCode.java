package org.sopt.sopkathon.global.response.code;

import org.springframework.http.HttpStatus;

public enum CommonSuccessCode implements SuccessCode {

    OK(HttpStatus.OK, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "생성이 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CommonSuccessCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override public HttpStatus getHttpStatus() { return httpStatus; }
    @Override public String getMessage() { return message; }
}