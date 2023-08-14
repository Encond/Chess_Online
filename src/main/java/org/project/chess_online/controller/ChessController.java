package org.project.chess_online.controller;

import org.project.chess_online.dto.LapDTO;
import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.ChessPieceMove;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.project.chess_online.facade.LapFacade;
import org.project.chess_online.security.JWTTokenProvider;
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
    private final GameHistoryService gameHistoryService;
    private final JWTTokenProvider jwtTokenProvider;
    private final List<User> userQueue;

    @Autowired
    public ChessController(LapService lapService, UserService userService, LapFacade lapFacade, ChatService chatService,
                           GameHistoryService gameHistoryService, JWTTokenProvider jwtTokenProvider) {
        this.lapService = lapService;
        this.userService = userService;
        this.lapFacade = lapFacade;
        this.chatService = chatService;
        this.gameHistoryService = gameHistoryService;
        this.jwtTokenProvider = jwtTokenProvider;

        this.userQueue = new ArrayList<>();
    }

    @PostMapping("/queue")
    public ResponseEntity<Boolean> enterGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);
            User user = this.userService.findById(userId);

            if (user != null) {
                if (!this.userQueue.isEmpty() && !this.userQueue.contains(user)) {
                    User userEnemy = this.userQueue.get(0);

                    this.lapService.create(userEnemy, user);
                    this.userQueue.remove(userEnemy);
                } else if (!this.userQueue.contains(user))
                    this.userQueue.add(user);

                return ResponseEntity.ok(true);
            }
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/cansel")
    public ResponseEntity<Boolean> cancelGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);
            User user = this.userService.findById(userId);

            if (user != null) {
                if (this.userQueue.contains(user)) {
                    this.userQueue.remove(user);
                }

                return ResponseEntity.ok(true);
            }
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkLap(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            return ResponseEntity.ok(this.lapService.findByUserId(userId) != null);
        }

        return ResponseEntity.ok(false);
    }

    @GetMapping("/lap")
    public ResponseEntity<LapDTO> getGame(@RequestHeader String token, Long lapId) {
        if (token != null) {
            if (this.jwtTokenProvider.validateToken(token)) {
                Long userId = this.jwtTokenProvider.getUserIdFromToken(token);
                LapDTO lapDTO = this.lapFacade.lapToLapDTO(this.lapService.findById(lapId), userId);

                return new ResponseEntity<>(lapDTO, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/play")
    public ResponseEntity<LapDTO> playGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            Lap tempLap = this.lapService.findByUserId(userId);
            Chat tempChat = this.chatService.createChat(tempLap);

            if (tempChat != null) {
                tempLap.setChat(tempChat);
                LapDTO tempLapDTO = this.lapFacade.lapToLapDTO(tempLap, userId);
                return new ResponseEntity<>(tempLapDTO, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/play/makeMove")
    public ResponseEntity.BodyBuilder makeMove(@RequestHeader String token, ChessPieceMove chessPieceMove) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            Lap tempLap = this.lapService.findByUserId(chessPieceMove.getUser().getIdUser());
            List<ChessPieceMove> tempChessPieceMoves = tempLap.getGameHistory().getChessPieceMoves();

            if (this.gameHistoryService.checkLastMove(tempChessPieceMoves, userId))
                this.gameHistoryService.add(tempLap.getGameHistory(), chessPieceMove);

            return ResponseEntity.ok();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/play/getMove")
    public ResponseEntity<Boolean> getMove(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            Lap tempLap = this.lapService.findByUserId(userId);
            List<ChessPieceMove> tempChessPieceMoves = tempLap.getGameHistory().getChessPieceMoves();

            return new ResponseEntity<>(tempChessPieceMoves.get(tempChessPieceMoves.size() - 1).getUser().getIdUser().equals(userId), HttpStatus.OK);
        }

        return ResponseEntity.ofNullable(false);
    }
}
