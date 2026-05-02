package com.mediabox.mediabox.Service.Post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediabox.mediabox.Exceptions.ResourceNotFoundException;
import com.mediabox.mediabox.Exceptions.UnauthorizedActionException;
import com.mediabox.mediabox.Models.Post.Post;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.Post.PostRepo;
import com.mediabox.mediabox.Repository.User.UserRepository;
import com.mediabox.mediabox.ServiceInterface.Post.PostService;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@Service
public class PostServiceimpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Post post, Long userId) {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(userServices.FindUserById(userId));
        newPost.setCreatedAt(LocalDateTime.now());
        return postRepo.save(newPost);

    }

    @Override
    public List<Post> getPostByuserId(Long userId) {

        return postRepo.findpostByUserId(userId);

    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public String deletePost(Long postid, Long userId) {
        Post post = findPostById(postid);
        User user = userServices.FindUserById(userId);

        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not authorized to delete this post");
        }
        postRepo.delete(post);
        return "Post is  deleted successfully";

    }

    @Override
    public String savedPost(Long postid, Long userId) {
        Post post = findPostById(postid);
        User user = userServices.FindUserById(userId);
        if (user.getSavedPosts().contains(post)) {
            user.getSavedPosts().remove(post);
            return "Post is removed from saved posts";

        } else {
            user.getSavedPosts().add(post);
        }

        userRepository.save(user);
        return "Post is added to saved posts";

    }

    @Override
    public boolean LikePost(Long postId, Long userId) {
        Post post = findPostById(postId);
        User user = userServices.FindUserById(userId);
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
            postRepo.save(post);

            return false;
        } else {
            post.getLiked().add(user);
            postRepo.save(post);

            return true;
        }

    }

    @Override
    public Post findPostById(Long postId) {
        return postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

}
