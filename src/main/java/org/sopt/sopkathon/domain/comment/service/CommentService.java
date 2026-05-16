package org.sopt.sopkathon.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon.domain.comment.code.CommentErrorCode;
import org.sopt.sopkathon.domain.comment.dto.request.CreateCommentRequest;
import org.sopt.sopkathon.domain.comment.dto.response.CreateCommentResponse;
import org.sopt.sopkathon.domain.comment.entity.Comment;
import org.sopt.sopkathon.domain.comment.repository.CommentRepository;
import org.sopt.sopkathon.domain.commentLike.entity.CommentLike;
import org.sopt.sopkathon.domain.commentLike.repository.CommentLikeRepository;
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
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // 게시글 댓글 생성
    @Transactional
    public CreateCommentResponse createComment(Long memberId, Long postId, CreateCommentRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.create(post, member, request.content());
        commentRepository.save(comment);
        post.increaseCommentCount();

        return CreateCommentResponse.from(comment);
    }

    // 게시글 댓글 좋아요
    @Transactional
    public void likeComment(Long memberId, Long postId, Long commentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MemberErrorCode.MEMBER_NOT_FOUND));

        postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(PostErrorCode.POST_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(CommentErrorCode.COMMENT_NOT_FOUND));

        if (commentLikeRepository.existsByMemberIdAndCommentId(memberId, commentId)) {
            throw new BusinessException(CommentErrorCode.COMMENT_LIKE_ALREADY_EXISTS);
        }

        commentLikeRepository.save(CommentLike.create(comment, member));
        comment.increaseCommentLikeCount();
    }
}