package org.sopt.sopkathon.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;

public record PostDetailResponse(
        Long postId,
        MemberInfo member,
        LocalDateTime createdAt,
        String content,
        int clapCount,
        int supportCount,
        int commentCount,
        boolean isClapped,
        boolean isSupported,
        List<CommentInfo> comments
) {
    public static PostDetailResponse of(Post post, List<Comment> comments,
                                        boolean isClapped, boolean isSupported, Set<Long> likedCommentIds) {
        return new PostDetailResponse(
                post.getId(),
                MemberInfo.from(post.getMember()),
                post.getCreatedAt(),
                post.getContent(),
                post.getClapCount(),
                post.getSupportCount(),
                post.getCommentCount(),
                isClapped,
                isSupported,
                comments.stream()
                        .map(comment -> CommentInfo.of(comment, likedCommentIds))
                        .toList()
        );
    }

    public record MemberInfo(
            Long memberId,
            String name,
            String profileImgUrl
    ) {
        public static MemberInfo from(Member member) {
            return new MemberInfo(
                    member.getId(),
                    member.getName(),
                    member.getProfileImgUrl()
            );
        }
    }

    public record CommentInfo(
            Long commentId,
            String content,
            MemberInfo member,
            LocalDateTime createdAt,
            int commentLikeCount,
            boolean isLiked
    ) {
        public static CommentInfo of(Comment comment, Set<Long> likedCommentIds) {
            return new CommentInfo(
                    comment.getId(),
                    comment.getContent(),
                    MemberInfo.from(comment.getMember()),
                    comment.getCreatedAt(),
                    comment.getCommentLikeCount(),
                    likedCommentIds.contains(comment.getId())
            );
        }
    }
}