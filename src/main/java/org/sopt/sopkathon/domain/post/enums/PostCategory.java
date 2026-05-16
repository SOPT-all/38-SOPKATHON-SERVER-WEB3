package org.sopt.sopkathon.domain.post.enums;

public enum PostCategory {
    KINDNESS, BOAST;

    public static PostCategory from(String postCategory) {
        try {
            return PostCategory.valueOf(postCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPostCategoryException();
        }
    }
}