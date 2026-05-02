package com.mediabox.mediabox.ServiceInterface.Messages;

import java.util.List;

import com.mediabox.mediabox.Models.Message.Message;
import com.mediabox.mediabox.Models.User.User;

public interface MessageService {

    public Message createMessage(User user, Long chatId, Message req);

    public List<Message> getAllchatMessages(Long chatId);

}
