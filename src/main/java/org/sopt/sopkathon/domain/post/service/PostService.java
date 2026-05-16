package org.sopt.sopkathon.domain.post.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.clap.repository.ClapRepository;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.sopt.sopkathon.domain.comment.repository.CommentRepository;
import org.sopt.sopkathon.domain.commentLike.repository.CommentLikeRepository;
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
import org.sopt.sopkathon.domain.support.repository.SupportRepository;
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
    private final ClapRepository clapRepository;
    private final SupportRepository supportRepository;
    private final CommentLikeRepository commentLikeRepository;

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
    public PostListResponse findPostsByCategory(Long memberId, PostCategory category) {
        List<Post> posts = postRepository.findPostsByCategory(category);
        List<Long> postIds = posts.stream().map(Post::getId).toList();

        Set<Long> clappedPostIds = new HashSet<>(clapRepository.findPostIdsByMemberIdAndPostIdIn(memberId, postIds));
        Set<Long> supportedPostIds = new HashSet<>(supportRepository.findPostIdsByMemberIdAndPostIdIn(memberId, postIds));

        return PostListResponse.of(posts, clappedPostIds, supportedPostIds);
    }

    //게시글 상세 조회
    public PostDetailResponse findPostDetailById(Long memberId, Long postId) {
        Post post = postRepository.findByIdWithMember(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<Long> commentIds = comments.stream().map(Comment::getId).toList();

        boolean isClapped = clapRepository.existsByMemberIdAndPostId(memberId, postId);
        boolean isSupported = supportRepository.existsByMemberIdAndPostId(memberId, postId);
        Set<Long> likedCommentIds = new HashSet<>(commentLikeRepository.findCommentIdsByMemberIdAndCommentIdIn(memberId, commentIds));

        return PostDetailResponse.of(post, comments, isClapped, isSupported, likedCommentIds);
    }
}