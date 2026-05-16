package org.sopt.sopkathon.domain.support.repository;

import java.util.List;
import org.sopt.sopkathon.domain.support.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupportRepository extends JpaRepository<Support, Long> {

    boolean existsByMemberIdAndPostId(Long memberId, Long postId);

    @Query("SELECT s.post.id FROM Support s WHERE s.member.id = :memberId AND s.post.id IN :postIds")
    List<Long> findPostIdsByMemberIdAndPostIdIn(@Param("memberId") Long memberId, @Param("postIds") List<Long> postIds);
}