package org.sopt.sopkathon.domain.clap.repository;

import org.sopt.sopkathon.domain.clap.entity.Clap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClapRepository extends JpaRepository<Clap, Long> {

    boolean existsByMemberIdAndPostId(Long memberId, Long postId);
}