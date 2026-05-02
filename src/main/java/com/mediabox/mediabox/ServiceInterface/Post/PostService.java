package com.mediabox.mediabox.ServiceInterface.Post;

import java.util.List;

import com.mediabox.mediabox.Models.Post.Post;

public interface PostService {

    public Post createPost(Post post, Long userId);

    public List<Post> getPostByuserId(Long userId);

    public List<Post> getAllPosts();

    public String deletePost(Long postid, Long userId);

    public Post findPostById(Long postId);

    public boolean LikePost(Long postId, Long userId);

    public String savedPost(Long postid, Long userId);
}
