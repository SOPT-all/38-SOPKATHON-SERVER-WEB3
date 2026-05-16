package org.sopt.sopkathon.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;

public record PostListResponse(List<PostInfo> posts) {

    public static PostListResponse of(List<Post> posts, Set<Long> clappedPostIds, Set<Long> supportedPostIds) {
        return new PostListResponse(
                posts.stream()
                        .map(post -> PostInfo.of(post, clappedPostIds, supportedPostIds))
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
            int commentCount,
            boolean isClapped,
            boolean isSupported
    ) {
        public static PostInfo of(Post post, Set<Long> clappedPostIds, Set<Long> supportedPostIds) {
            return new PostInfo(
                    post.getId(),
                    MemberInfo.from(post.getMember()),
                    post.getCreatedAt(),
                    post.getContent(),
                    post.getClapCount(),
                    post.getSupportCount(),
                    post.getCommentCount(),
                    clappedPostIds.contains(post.getId()),
                    supportedPostIds.contains(post.getId())
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