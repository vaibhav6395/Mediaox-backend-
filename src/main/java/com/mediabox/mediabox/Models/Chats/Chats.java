package com.mediabox.mediabox.Models.Chats;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mediabox.mediabox.Models.Message.Message;
import com.mediabox.mediabox.Models.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String chat_name;
    private String chatimg;
    @ManyToMany
    private List<User> users = new ArrayList<>();

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();
}