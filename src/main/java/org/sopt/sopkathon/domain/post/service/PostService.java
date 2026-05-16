package org.sopt.sopkathon.domain.post.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.sopt.sopkathon.domain.comment.repository.CommentRepository;
import org.sopt.sopkathon.domain.member.code.MemberErrorCode;
import org.sopt.sopkathon.domain.member.entity.Member;
import org.sopt.sopkathon.domain.member.repository.MemberRepository;
import org.sopt.sopkathon.domain.post.code.PostErrorCode;
import org.sopt.sopkathon.domain.post.dto.request.CreatePostRequest;
import org.sopt.sopkathon.domain.post.dto.response.CreatePostResponse;
import org.sopt.sopkathon.domain.post.dto.response.PostDetailResponse;
import org.sopt.sopkathon.domain.post.dto.response.PostListResponse;
import org.sopt.sopkathon.domain.post.entity.Post;
import org.sopt.sopkathon.domain.post.enums.PostCategory;
import org.sopt.sopkathon.domain.post.repository.PostRepository;
import org.sopt.sopkathon.global.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    // 게시글 생성
    @Transactional
    public CreatePostResponse createPost(Long memberId, CreatePostRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        Post post = Post.create(member, request.content(), request.category());
        postRepository.save(post);

        return CreatePostResponse.from(post);
    }

    //게시글 리스트 조회
    public PostListResponse findPostsByCategory(PostCategory category) {
        List<Post> posts = postRepository.findPostsByCategory(category);

        return PostListResponse.of(posts);

    }

    //게시글 상세 조회
    public PostDetailResponse findPostDetailById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return PostDetailResponse.of(post, comments);
    }
}