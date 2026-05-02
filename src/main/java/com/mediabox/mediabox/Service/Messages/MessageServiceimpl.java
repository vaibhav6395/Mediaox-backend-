package com.mediabox.mediabox.Service.Messages;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.Message.Message;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Repository.Chats.ChatsReposiory;
import com.mediabox.mediabox.Repository.Messages.MessagesRepository;
import com.mediabox.mediabox.ServiceInterface.Messages.MessageService;
import com.mediabox.mediabox.ServiceInterface.chats.ChatsService;

@Service
public class MessageServiceimpl implements MessageService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private ChatsReposiory chatsReposiory;

    @Override
    public Message createMessage(User user, Long chatId, Message req) {
        Message newMessage = new Message();
        Chats chat = chatsService.findChatById(chatId);
        newMessage.setChat(chat);
        newMessage.setContent(req.getContent());
        newMessage.setImage(req.getImage());
        newMessage.setUser(user);
        newMessage.setTimestamp(LocalDateTime.now());
        Message msg = messagesRepository.save(newMessage);
        chat.getMessages().add(msg);
        chatsReposiory.save(chat);
        return msg;
    }

    @Override
    public List<Message> getAllchatMessages(Long chatId) {

        chatsService.findChatById(chatId);
        return messagesRepository.findByChat_Id(chatId);

    }

}
