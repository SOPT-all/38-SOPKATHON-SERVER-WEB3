package org.sopt.sopkathon.domain.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.global.entity.BaseTimeEntity;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int commentLikeCount = 0;

    private Comment(Post post, Member member, String content) {
        this.post = post;
        this.member = member;
        this.content = content;
    }

    public static Comment create(Post post, Member member, String content) {
        return new Comment(post, member, content);
    }

    public void increaseCommentLikeCount() {
        this.commentLikeCount++;
    }
}