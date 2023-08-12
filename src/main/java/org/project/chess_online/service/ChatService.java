package org.project.chess_online.service;

import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.Message;
import org.project.chess_online.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat findById(Long id) {
        return this.chatRepository.findById(id).orElseGet(Chat::new);
    }

    public Chat createChat(Lap lap) {
        Chat chat = new Chat(lap);

        return this.chatRepository.save(chat);
    }

    public void sendMessage(Chat chat, Message message) {
        if (message != null && !message.getText().isEmpty()) {
            List<Message> tempMessages = chat.getMessages();
            tempMessages.add(message);
            chat.setMessages(tempMessages);

            this.chatRepository.save(chat);
        }
    }
}
