package org.sopt.sopkathon.domain.post.enums;

import org.sopt.sopkathon.domain.post.code.PostErrorCode;
import org.sopt.sopkathon.global.exception.BusinessException;

public enum PostCategory {
    KINDNESS,
    BOAST;

    public static PostCategory from(String postCategory) {
        try {
            return PostCategory.valueOf(postCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(PostErrorCode.INVALID_POST_FILTER);
        }
    }
}