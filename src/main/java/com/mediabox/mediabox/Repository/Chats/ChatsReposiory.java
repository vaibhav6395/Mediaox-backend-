package com.mediabox.mediabox.Repository.Chats;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mediabox.mediabox.Models.Chats.Chats;
import com.mediabox.mediabox.Models.User.User;

public interface ChatsReposiory extends JpaRepository<Chats, Long> {

    public List<Chats> findByUsers_Id(Long Id);

    @Query("select c from Chats c where :user member of c.users and :requser member of c.users")
    public Chats finChatsByUserId(@Param("user") User user, @Param("requser") User requser);

}
