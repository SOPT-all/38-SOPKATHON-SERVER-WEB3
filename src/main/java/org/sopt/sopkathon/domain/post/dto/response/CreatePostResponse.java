package org.sopt.sopkathon.domain.post.dto.response;

import java.time.LocalDateTime;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.enums.PostCategory;

public record CreatePostResponse(
        Long postId,
        String content,
        PostCategory category,
        int clapCount,
        int supportCount,
        int commentCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CreatePostResponse from(Post post) {
        return new CreatePostResponse(
                post.getId(),
                post.getContent(),
                post.getCategory(),
                post.getClapCount(),
                post.getSupportCount(),
                post.getCommentCount(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}