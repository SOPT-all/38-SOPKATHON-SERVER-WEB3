package org.sopt.sopkathon.domain.post.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.global.response.code.SuccessCode;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostSuccessCode implements SuccessCode {

    CREATE_POST(HttpStatus.CREATED, "게시글 생성 성공"),
    FIND_POST_LIST(HttpStatus.OK, "게시글 리스트 조회 성공"),
    READ_POST_DETAIL(HttpStatus.OK, "게시글 상세 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}