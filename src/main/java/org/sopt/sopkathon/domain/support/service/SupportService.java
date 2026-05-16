package org.sopt.sopkathon.domain.support.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.member.code.MemberErrorCode;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.member.repository.MemberRepository;
import org.sopt.sopkathon.domain.post.code.PostErrorCode;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.repository.PostRepository;
import org.sopt.sopkathon.domain.support.code.SupportErrorCode;
import org.sopt.sopkathon.domain.support.entity.Support;
import org.sopt.sopkathon.domain.support.repository.SupportRepository;
import org.sopt.sopkathon.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final SupportRepository supportRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void support(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        if (supportRepository.existsByMemberIdAndPostId(memberId, postId)) {
            throw new BusinessException(SupportErrorCode.SUPPORT_ALREADY_EXISTS);
        }

        supportRepository.save(Support.create(member, post));
        post.increaseSupportCount();
    }
}