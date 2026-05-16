package org.sopt.sopkathon.domain.clap.repository;

import java.util.List;
import org.sopt.sopkathon.domain.clap.entity.Clap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClapRepository extends JpaRepository<Clap, Long> {

    boolean existsByMemberIdAndPostId(Long memberId, Long postId);

    @Query("SELECT c.post.id FROM Clap c WHERE c.member.id = :memberId AND c.post.id IN :postIds")
    List<Long> findPostIdsByMemberIdAndPostIdIn(@Param("memberId") Long memberId, @Param("postIds") List<Long> postIds);
}