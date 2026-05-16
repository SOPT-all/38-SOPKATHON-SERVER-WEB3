package org.sopt.sopkathon.domain.member.dto.response;

import java.util.List;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.enums.PostCategory;

public record MemberPageResponse(
        String nickname,
        String profileImgUrl,
        List<PostInfo> posts
) {

    public static MemberPageResponse of(Member member, List<Post> posts) {
        return new MemberPageResponse(
                member.getName(),
                member.getProfileImgUrl(),
                posts.stream()
                        .map(PostInfo::from)
                        .toList()
        );
    }

    public record PostInfo(
            Long postId,
            PostCategory category,
            String content,
            int clapCount,
            int supportCount,
            int commentCount
    ) {

        public static PostInfo from(Post post) {
            return new PostInfo(
                    post.getId(),
                    post.getCategory(),
                    post.getContent(),
                    post.getClapCount(),
                    post.getSupportCount(),
                    post.getCommentCount()
            );
        }
    }
}