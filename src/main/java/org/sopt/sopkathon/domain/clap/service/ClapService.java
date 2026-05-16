package org.sopt.sopkathon.domain.clap.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.clap.code.ClapErrorCode;
import org.sopt.sopkathon.domain.clap.entity.Clap;
import org.sopt.sopkathon.domain.clap.repository.ClapRepository;
import org.sopt.sopkathon.domain.member.code.MemberErrorCode;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.member.repository.MemberRepository;
import org.sopt.sopkathon.domain.post.code.PostErrorCode;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.repository.PostRepository;
import org.sopt.sopkathon.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClapService {

    private final ClapRepository clapRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void clap(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        if (clapRepository.existsByMemberIdAndPostId(memberId, postId)) {
            throw new BusinessException(ClapErrorCode.CLAP_ALREADY_EXISTS);
        }

        clapRepository.save(Clap.create(member, post));
        post.increaseClapCount();
    }
}