package com.mediabox.mediabox.Repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediabox.mediabox.Models.Comments.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
