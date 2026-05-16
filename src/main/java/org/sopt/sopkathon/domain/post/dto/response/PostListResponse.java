package org.sopt.sopkathon.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;

public record PostListResponse(
        List<PostInfo> posts
) {

    public static PostListResponse of(List<Post> posts) {
        return new PostListResponse(
                posts.stream()
                        .map(PostInfo::from)
                        .toList()
        );
    }

    public record PostInfo(
            Long postId,
            MemberInfo member,
            LocalDateTime createdAt,
            String content,
            int clapCount,
            int supportCount,
            int commentCount
    ) {

        public static PostInfo from(Post post) {
            return new PostInfo(
                    post.getId(),
                    MemberInfo.from(post.getMember()),
                    post.getCreatedAt(),
                    post.getContent(),
                    post.getClapCount(),
                    post.getSupportCount(),
                    post.getCommentCount()
            );
        }
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
}