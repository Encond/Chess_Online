package org.project.chess_online.service;

import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.Message;
import org.project.chess_online.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Chat chat, String text) {
        Message message = new Message();
        message.setChat(chat);
        message.setText(text);
        message.setDate(new Date());

        return this.messageRepository.save(message);
    }
}
