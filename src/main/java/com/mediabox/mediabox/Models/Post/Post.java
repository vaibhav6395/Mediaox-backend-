package com.mediabox.mediabox.Models.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediabox.mediabox.Models.Comments.Comment;
import com.mediabox.mediabox.Models.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany
    private List<User> Liked = new ArrayList<>();
    private LocalDateTime createdAt;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
