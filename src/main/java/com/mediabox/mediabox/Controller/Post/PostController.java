package com.mediabox.mediabox.Controller.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.Post.Post;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Resposes.ApiResponses;
import com.mediabox.mediabox.ServiceInterface.Post.PostService;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserServices userservices;

    private ApiResponses responses;

    @PostMapping("/api/createPost")
    public ResponseEntity<ApiResponses> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) {
        Post newpost;
        try {
            User user = userservices.findUserBYjwt(jwt);
            newpost = postService.createPost(post, user.getId());
            responses = new ApiResponses("created sucessfully with id " + newpost.getId() + "", true);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/api/deletePost/{postId}")
    public ResponseEntity<ApiResponses> deletePost(@PathVariable Long postId,
            @RequestHeader("Authorization") String jwt) {
        ApiResponses response;
        try {
            User user = userservices.findUserBYjwt(jwt);

            postService.deletePost(postId, user.getId());
            response = new ApiResponses("Post deleted successfully", true);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/User")
    public ResponseEntity<List<Post>> findPostByUserId(@RequestHeader("Authorization") String jwt) {
        ApiResponses responses;
        try {
            User user = userservices.findUserBYjwt(jwt);

            List<Post> posts = postService.getPostByuserId(user.getId());
            responses = new ApiResponses(posts.toArray().toString(), true);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        ApiResponses responses;
        try {
            return ResponseEntity.ok(postService.getAllPosts());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping("/api/postId/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId) {
        try {
            responses = new ApiResponses(postService.findPostById(postId).toString(), true);
            return ResponseEntity.ok(postService.findPostById(postId));
        } catch (Exception e) {
            responses = new ApiResponses(e.getMessage(), false);
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/api/SavedPost/{postid}")
    public ResponseEntity<ApiResponses> getSavedPOst(@PathVariable Long postid,
            @RequestHeader("Authorization") String jwt) {

        try {
            User user = userservices.findUserBYjwt(jwt);

            responses = new ApiResponses(postService.savedPost(postid, user.getId()), true);
        } catch (Exception e) {
            responses = new ApiResponses(e.getMessage(), false);

            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/api/Like/{postId}")
    public ResponseEntity<ApiResponses> postMethodName(@PathVariable Long userid,
            @RequestHeader("Authorization") String jwt) {
        try {
            User user = userservices.findUserBYjwt(jwt);

            responses = new ApiResponses(
                    postService.LikePost(user.getId(), userid) ? "Post liked successfully"
                            : "Post unliked successfully",
                    true);
        } catch (Exception e) {
            responses = new ApiResponses(e.getMessage(), false);
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(responses);
    }

}
