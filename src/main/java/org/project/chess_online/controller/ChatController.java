package org.project.chess_online.controller;

import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.Message;
import org.project.chess_online.service.ChatService;
import org.project.chess_online.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("chatId") Long chatId) {
        Chat tempChat = this.chatService.findById(chatId);
        List<Message> messages = tempChat.getMessages();

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/{chatId}/message/send")
    public ResponseEntity.BodyBuilder sendMessage(@PathVariable("chatId") Long chatId, String text) {
        Chat tempChat = this.chatService.findById(chatId);

        if (tempChat != null) {
            Message message = this.messageService.createMessage(tempChat, text);

            if (message != null)
                this.chatService.sendMessage(tempChat, message);
        }

        return ResponseEntity.ok();
    }
}
