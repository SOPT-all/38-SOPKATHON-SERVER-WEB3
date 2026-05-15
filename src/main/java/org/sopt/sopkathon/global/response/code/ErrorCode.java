package org.sopt.sopkathon.global.response.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();
    String name();
    String getMessage();
}