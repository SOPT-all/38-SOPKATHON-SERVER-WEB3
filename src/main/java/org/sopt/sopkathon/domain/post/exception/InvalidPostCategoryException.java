package org.sopt.sopkathon.domain.post.exception;

import org.sopt.sopkathon.domain.post.code.PostErrorCode;
import org.sopt.sopkathon.global.exception.BusinessException;

public class InvalidPostCategoryException extends BusinessException {
    public InvalidPostCategoryException() {
        super(PostErrorCode.NOT_FOUND_POST_LIST);
    }
}
