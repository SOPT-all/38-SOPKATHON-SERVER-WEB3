package org.sopt.sopkathon.domain.member.repository;

import org.sopt.sopkathon.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}