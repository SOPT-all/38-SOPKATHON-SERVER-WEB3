package org.sopt.sopkathon.domain.post.dto.request;

import org.sopt.sopkathon.domain.post.enums.PostCategory;

public record CreatePostRequest(
        String content,
        PostCategory category
) {}