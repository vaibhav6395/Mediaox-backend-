package com.mediabox.mediabox.Controller.Chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.User.User;
import com.mediabox.mediabox.Request.Chats.Chatrequest;
import com.mediabox.mediabox.ServiceInterface.User.UserServices;
import com.mediabox.mediabox.ServiceInterface.chats.ChatsService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public Chats createChats(@RequestBody Chatrequest chatrequest, @RequestHeader("Authorization") String jwt) {
        User user = userServices.findUserBYjwt(jwt);
        User user2 = userServices.FindUserById(chatrequest.getUser2());
        Chats chats = chatsService.createChat(user, user2);

        return chats;
    }

    @GetMapping("/")
    public List<Chats> findUserChats(@RequestHeader("Authorization") String jwt) {

        User user = userServices.findUserBYjwt(jwt);
        List<Chats> chats = chatsService.findUserChats(user.getId());

        return chats;
    }

}
