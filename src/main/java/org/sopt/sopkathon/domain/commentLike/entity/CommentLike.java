package org.sopt.sopkathon.domain.commentLike.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.sopt.sopkathon.domain.member.entity.Member;

@Entity
@Table(name = "comment_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "comment_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private CommentLike(Comment comment, Member member) {
        this.comment = comment;
        this.member = member;
    }

    public static CommentLike create(Comment comment, Member member) {
        return new CommentLike(comment, member);
    }
}