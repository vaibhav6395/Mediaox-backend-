package com.mediabox.mediabox.Service.Chats;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediabox.mediabox.Exceptions.ResourceNotFoundException;
import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.Chats.ChatsReposiory;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;
import com.mediabox.mediabox.ServiceInterface.chats.ChatsService;

@Service
public class ChatServiceimpl implements ChatsService {

    @Autowired
    private ChatsReposiory chatsReposiory;
    @Autowired
    private UserServices userServices;

    @Override
    public Chats createChat(User reqUser, User user2) {

        Chats isexist = chatsReposiory.finChatsByUserId(user2, reqUser);
        if (isexist != null) {
            return isexist;
        }
        Chats chats = new Chats();
        chats.getUsers().add(user2);
        chats.getUsers().add(reqUser);
        chats.setTimestamp(LocalDateTime.now());
        return chatsReposiory.save(chats);
    }

    @Override
    public Chats findChatById(Long Id) {
        return chatsReposiory.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Chat", "id", Id));
    }

    @Override
    public List<Chats> findUserChats(Long Id) {
        return chatsReposiory.findByUsers_Id(Id);
    }

}
