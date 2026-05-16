package org.sopt.sopkathon.domain.commentLike.repository;

import org.sopt.sopkathon.domain.commentLike.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByMemberIdAndCommentId(Long memberId, Long commentId);
}