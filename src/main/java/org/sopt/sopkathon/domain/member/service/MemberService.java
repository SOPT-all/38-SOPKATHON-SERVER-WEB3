package org.sopt.sopkathon.domain.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.member.code.MemberErrorCode;
import org.sopt.sopkathon.domain.member.dto.response.MemberPageResponse;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.member.repository.MemberRepository;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.repository.PostRepository;
import org.sopt.sopkathon.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;


    public MemberPageResponse findMyPage(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));
        List<Post> posts = postRepository.findAllByMemberId(memberId);
        return MemberPageResponse.of(member, posts);
    }


}
