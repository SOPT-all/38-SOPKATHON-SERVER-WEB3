package org.sopt.sopkathon.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
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
        List<CommentInfo> comments
)  {

    public static PostDetailResponse of(Post post, List<Comment> comments) {
        return new PostDetailResponse(
                post.getId(),
                MemberInfo.from(post.getMember()),
                post.getCreatedAt(),
                post.getContent(),
                post.getClapCount(),
                post.getSupportCount(),
                post.getCommentCount(),
                comments.stream()
                        .map(CommentInfo::from)
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
            LocalDateTime createdAt
    ) {

        public static CommentInfo from(Comment comment) {
            return new CommentInfo(
                    comment.getId(),
                    comment.getContent(),
                    MemberInfo.from(comment.getMember()),
                    comment.getCreatedAt()
            );
        }
    }
}