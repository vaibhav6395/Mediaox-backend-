package com.mediabox.mediabox.ServiceInterface.chats;

import java.util.List;

import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.User.User;

public interface ChatsService {
    public Chats createChat(User reqUser, User user2);

    public Chats findChatById(Long Id);

    public List<Chats> findUserChats(Long Id);

}
