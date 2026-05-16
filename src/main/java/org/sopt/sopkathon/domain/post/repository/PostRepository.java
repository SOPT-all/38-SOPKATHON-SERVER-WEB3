package org.sopt.sopkathon.domain.post.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.enums.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        select p
        from Post p
        join fetch p.member m
        where p.category = :category
        order by p.createdAt desc
    """)
    List<Post> findPostsByCategory(PostCategory category);


    List<Post> findAllByMemberId(Long memberId);

    @Query("""
        select p
        from Post p
        join fetch p.member m
        where p.id = :postId
    """)
    Optional<Post> findByIdWithMember(@Param("postId") Long postId);
}