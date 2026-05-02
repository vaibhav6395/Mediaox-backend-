package com.mediabox.mediabox.Service.Comment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediabox.mediabox.Exceptions.BadRequestException;
import com.mediabox.mediabox.Exceptions.ResourceNotFoundException;
import com.mediabox.mediabox.Models.Comments.Comment;
import com.mediabox.mediabox.Models.Post.Post;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.Post.PostRepo;
import com.mediabox.mediabox.Repository.comment.CommentRepository;
import com.mediabox.mediabox.ServiceInterface.Comment.CommentService;
import com.mediabox.mediabox.ServiceInterface.Post.PostService;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostService postService;
    @Autowired
    private UserServices userServices;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    PostRepo postRepo;

    @Override
    public Comment createComment(Comment comment, Long postid, Long userId) {
        if (comment.getContent() == null || comment.getContent().isBlank()) {
            throw new BadRequestException("Comment content is required");
        }
        User user = userServices.FindUserById(userId);
        Post post = postService.findPostById(postid);
        comment.setUser(user);
        comment.setCreatedat(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepo.save(post);

        return savedComment;

    }

    @Override
    public Comment Likecomment(Long commentId, Long userID) {

        Comment comment = findcommentbyId(commentId);
        User user = userServices.FindUserById(userID);
        if (comment.getLike().contains(user)) {
            comment.getLike().remove(user);
            return commentRepository.save(comment);
        }

        comment.getLike().add(user);

        return commentRepository.save(comment);

    }

    @Override
    public Comment findcommentbyId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
    }

}
