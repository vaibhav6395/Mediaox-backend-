package com.mediabox.mediabox.Models.Message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Content;
    private String image;

    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Chats chat;

    private LocalDateTime timestamp;

}
