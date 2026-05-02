package com.mediabox.mediabox.Controller.Messages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.Message.Message;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.ServiceInterface.Messages.MessageService;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserServices userServices;

    @PostMapping("/create/{chatId}")
    public Message createMessage(@RequestBody Message msg, @RequestHeader("Authorization") String jwt,
            @PathVariable Long chatId) throws Exception {

        User user = userServices.findUserBYjwt(jwt);
        Message message = messageService.createMessage(user, chatId, msg);

        return message;
    }

    @GetMapping("/msg/{chatId}")
    public List<Message> findChatMessages(@RequestHeader("Authorization") String jwt,
            @PathVariable Long chatId) throws Exception {

        User user = userServices.findUserBYjwt(jwt);
        List<Message> messages = messageService.getAllchatMessages(chatId);
        return messages;
    }

}
