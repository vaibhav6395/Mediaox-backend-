package com.mediabox.mediabox.Repository.Messages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediabox.mediabox.Models.Message.Message;

public interface MessagesRepository extends JpaRepository<Message, Long> {

    public List<Message> findByChat_Id(Long chatId);

}
