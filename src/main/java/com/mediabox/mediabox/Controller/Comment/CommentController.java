package com.mediabox.mediabox.Controller.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.Comments.Comment;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.ServiceInterface.Comment.CommentService;
import com.mediabox.mediabox.ServiceInterface.Post.PostService;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserServices usweServices;
    @Autowired
    private PostService postService;

    @PostMapping("/create/{postid}")
    public Comment CreateComment(@RequestBody Comment comment, @PathVariable Long postid,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = usweServices.findUserBYjwt(jwt);

        Comment createdcomment = commentService.createComment(comment, postService.findPostById(postid).getId(),
                user.getId());
        return createdcomment;
    }

    @PutMapping("/Like/{commentid}")
    public Comment LikeComment(@PathVariable Long commentid,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = usweServices.findUserBYjwt(jwt);

        Comment Likedcommet = commentService.Likecomment(commentid, user.getId());
        return Likedcommet;
    }

}
