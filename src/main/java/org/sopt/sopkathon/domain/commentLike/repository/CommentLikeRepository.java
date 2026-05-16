package org.sopt.sopkathon.domain.commentLike.repository;

import java.util.List;
import org.sopt.sopkathon.domain.commentLike.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByMemberIdAndCommentId(Long memberId, Long commentId);

    @Query("SELECT cl.comment.id FROM CommentLike cl WHERE cl.member.id = :memberId AND cl.comment.id IN :commentIds")
    List<Long> findCommentIdsByMemberIdAndCommentIdIn(@Param("memberId") Long memberId, @Param("commentIds") List<Long> commentIds);
}