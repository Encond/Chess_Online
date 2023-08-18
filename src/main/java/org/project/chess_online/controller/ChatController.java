package org.project.chess_online.controller;

import org.project.chess_online.dto.MessageDTO;
import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.Message;
import org.project.chess_online.facade.MessageFacade;
import org.project.chess_online.security.JWTTokenProvider;
import org.project.chess_online.service.ChatService;
import org.project.chess_online.service.LapService;
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
    private final LapService lapService;
    private final MessageFacade messageFacade;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService, LapService lapService, MessageFacade messageFacade, JWTTokenProvider jwtTokenProvider) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.lapService = lapService;
        this.messageFacade = messageFacade;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/messages/get")
    public ResponseEntity<List<MessageDTO>> getMessages(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            Lap tempLap = this.lapService.findByUserId(userId);

            Chat tempChat = this.chatService.findByLapId(tempLap.getIdLap());
            List<Message> messages = tempChat.getMessages();

            List<MessageDTO> messageDTOS = this.messageFacade.messageToMessageDTO(messages, userId.equals(tempLap.getUserFirst().getIdUser()));

            if (messageDTOS != null)
                return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/messages/send")
    public ResponseEntity.BodyBuilder sendMessage(@RequestHeader String token, String text) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            Lap createdLap = this.lapService.findByUserId(userId);
            Chat tempChat = this.chatService.findByLapId(createdLap.getIdLap());

            if (tempChat != null) {
                Message message = this.messageService.createMessage(tempChat, text);

                if (message != null)
                    this.chatService.sendMessage(tempChat, message);
            }

            return ResponseEntity.ok();
        }

        return ResponseEntity.badRequest();
    }
}
