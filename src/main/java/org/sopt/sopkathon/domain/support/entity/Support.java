package org.sopt.sopkathon.domain.support.entity;

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
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.post.entity.Post;

@Entity
@Table(name = "supports", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "post_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Support(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

    public static Support create(Member member, Post post) {
        return new Support(member, post);
    }
}