package org.sopt.sopkathon.domain.comment.repository;

import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}