package org.sopt.sopkathon.domain.support.repository;

import org.sopt.sopkathon.domain.support.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Long> {

    boolean existsByMemberIdAndPostId(Long memberId, Long postId);
}