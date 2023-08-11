package org.project.chess_online.controller;

import org.project.chess_online.dto.LapDTO;
import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.GameHistory;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.project.chess_online.facade.LapFacade;
import org.project.chess_online.service.ChatService;
import org.project.chess_online.service.GameHistoryService;
import org.project.chess_online.service.LapService;
import org.project.chess_online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin
public class ChessController {
    private final LapService lapService;
    private final LapFacade lapFacade;
    private final UserService userService;
    private final ChatService chatService;
    private List<User> userQueue;

    @Autowired
    public ChessController(LapService lapService, UserService userService, LapFacade lapFacade, ChatService chatService) {
        this.lapService = lapService;
        this.userService = userService;
        this.lapFacade = lapFacade;
        this.chatService = chatService;

        this.userQueue = new ArrayList<User>();
    }

    @GetMapping("/queue")
    public ResponseEntity.BodyBuilder enterGame(Long userId, boolean status) {
        User tempUser = this.userService.findById(userId);
        boolean tempResult = tempUser != null && !status ? this.userQueue.remove(tempUser) : this.userQueue.add(tempUser);

        return tempResult ? ResponseEntity.ok() : ResponseEntity.internalServerError();
    }

    @PostMapping("/create")
    public ResponseEntity<Lap> createGame() {
        if (this.userQueue.size() < 2)
            return new ResponseEntity<>(null, HttpStatus.OK);

        User userFirst = this.userQueue.get(1);
        User userSecond = this.userQueue.get(2);

        this.lapService.create(userFirst, userSecond);
        Lap createdLap = this.lapService.findByUsers(userFirst, userSecond);

        return new ResponseEntity<>(createdLap, HttpStatus.OK);
    }

    @GetMapping("/play")
    public ResponseEntity<LapDTO> playGame(Long userId) {
        Lap tempLap = this.lapService.findByUser(userId);
        Chat tempChat = this.chatService.createChat(tempLap);

        if (tempChat != null) {
            tempLap.setChat(tempChat);
            LapDTO tempLapDTO = this.lapFacade.lapToLapDTO(tempLap, userId);
            return new ResponseEntity<>(tempLapDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/play/makeMove")
//    public ResponseEntity<LapDTO> makeMove(String username) {
//
//
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
}
