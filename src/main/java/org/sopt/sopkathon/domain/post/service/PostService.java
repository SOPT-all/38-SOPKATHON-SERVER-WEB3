package org.sopt.sopkathon.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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