package org.sopt.sopkathon.domain.comment.dto.response;

import java.time.LocalDateTime;
import org.sopt.sopkathon.domain.comment.entity.Comment;

public record CreateCommentResponse(
        String content,
        LocalDateTime createdAt,
        int commentLikeCount,
        String authorProfileImgUrl,
        String authorNickname
) {
    public static CreateCommentResponse from(Comment comment) {
        return new CreateCommentResponse(
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getCommentLikeCount(),
                comment.getMember().getProfileImgUrl(),
                comment.getMember().getName()
        );
    }
}