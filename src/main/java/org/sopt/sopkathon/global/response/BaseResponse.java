package org.sopt.sopkathon.global.response;

import org.sopt.sopkathon.global.response.code.ErrorCode;
import org.sopt.sopkathon.global.response.code.SuccessCode;

public record BaseResponse<T>(
        boolean success,
        String code,
        String message,
        T data
) {
    public static <T> BaseResponse<T> success(SuccessCode successCode, T data) {
        return new BaseResponse<>(true, successCode.name(), successCode.getMessage(), data);
    }

    public static BaseResponse<Void> success(SuccessCode successCode) {
        return new BaseResponse<>(true, successCode.name(), successCode.getMessage(), null);
    }

    public static BaseResponse<Void> error(ErrorCode errorCode) {
        return new BaseResponse<>(false, errorCode.name(), errorCode.getMessage(), null);
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode, T data) {
        return new BaseResponse<>(false, errorCode.name(), errorCode.getMessage(), data);
    }
}