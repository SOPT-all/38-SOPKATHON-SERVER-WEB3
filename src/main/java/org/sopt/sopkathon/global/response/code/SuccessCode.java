package org.sopt.sopkathon.global.response.code;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    HttpStatus getHttpStatus();
    String name();
    String getMessage();
}