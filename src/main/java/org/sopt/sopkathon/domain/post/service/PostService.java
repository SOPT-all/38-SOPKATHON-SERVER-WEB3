package org.sopt.sopkathon.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.member.code.MemberErrorCode;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.member.repository.MemberRepository;
import org.sopt.sopkathon.domain.post.dto.request.CreatePostRequest;
import org.sopt.sopkathon.domain.post.dto.response.CreatePostResponse;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.repository.PostRepository;
import org.sopt.sopkathon.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 생성
    @Transactional
    public CreatePostResponse createPost(Long memberId, CreatePostRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        Post post = Post.create(member, request.content(), request.category());
        postRepository.save(post);

        return CreatePostResponse.from(post);
    }
}