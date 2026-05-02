package com.mediabox.mediabox.ServiceInterface.Comment;

import com.mediabox.mediabox.Models.Comments.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Long postid, Long userId);

    public Comment Likecomment(Long commentId, Long userID);

    public Comment findcommentbyId(Long commentId);

}
