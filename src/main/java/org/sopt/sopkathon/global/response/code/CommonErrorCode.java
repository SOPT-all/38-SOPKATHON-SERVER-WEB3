package org.sopt.sopkathon.global.response.code;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode implements ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_PATH_VARIABLE(HttpStatus.BAD_REQUEST, "잘못된 경로 변수입니다."),
    INVALID_QUERY_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 쿼리 파라미터입니다."),
    INVALID_JSON(HttpStatus.BAD_REQUEST, "올바르지 않은 JSON 형식입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CommonErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override public HttpStatus getHttpStatus() { return httpStatus; }
    @Override public String getMessage() { return message; }
}