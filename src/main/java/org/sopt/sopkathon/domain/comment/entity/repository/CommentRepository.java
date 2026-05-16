package org.sopt.sopkathon.domain.comment.entity.repository;

import java.util.List;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("""
            select c
            from Comment c
            join fetch c.member
            where c.post.id = :postId
            order by c.createdAt asc
            """)
    List<Comment> findAllByPostId(Long postId);
}