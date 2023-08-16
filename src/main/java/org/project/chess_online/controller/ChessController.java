package org.project.chess_online.controller;

import org.project.chess_online.dto.ChessPieceMoveDTO;
import org.project.chess_online.dto.LapDTO;
import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.ChessPieceMove;
import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.project.chess_online.facade.ChessPieceMoveFacade;
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
    private final ChessPieceMoveFacade chessPieceMoveFacade;
    private final UserService userService;
    private final ChatService chatService;
    private final GameHistoryService gameHistoryService;
    private final JWTTokenProvider jwtTokenProvider;
    private final List<User> userQueue;

    @Autowired
    public ChessController(LapService lapService, ChessPieceMoveFacade chessPieceMoveFacade, UserService userService, LapFacade lapFacade, ChatService chatService,
                           GameHistoryService gameHistoryService, JWTTokenProvider jwtTokenProvider) {
        this.lapService = lapService;
        this.chessPieceMoveFacade = chessPieceMoveFacade;
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

                    if (this.lapService.findByUserId(userEnemy.getIdUser()) == null) {
                        Lap createdLap = this.lapService.create(userEnemy, user);

                        Chat createdChat = this.chatService.createChat(createdLap);
                        createdLap.setChat(createdChat);

                        return ResponseEntity.ok(true);
                    }

                    return ResponseEntity.ok(this.userQueue.remove(userEnemy));
                } else if (!this.userQueue.contains(user))
                    return ResponseEntity.ok(this.userQueue.add(user));
            }
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Boolean> cancelGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);
            User user = this.userService.findById(userId);

            if (user != null) {
                if (this.userQueue.contains(user)) {
                    this.userQueue.remove(user);

                    return ResponseEntity.ok(true);
                }
            }
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkLap(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            if (userId != null && userId >= 1) {
                boolean result = this.lapService.findByUserId(userId) != null;
                return ResponseEntity.ok(result);
            }
        }

        return ResponseEntity.ok(false);
    }

    @GetMapping("/lap")
    public ResponseEntity<LapDTO> getGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            if (userId != null && userId >= 1) {
                LapDTO lapDTO = this.lapFacade.lapToLapDTO(this.lapService.findByUserId(userId), userId);

                return ResponseEntity.ok(lapDTO);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/play")
    public ResponseEntity<LapDTO> playGame(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            if (userId != null && userId >= 1) {
                Lap tempLap = this.lapService.findByUserId(userId);
                LapDTO tempLapDTO = this.lapFacade.lapToLapDTO(tempLap, userId);

                return ResponseEntity.ok(tempLapDTO);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/play/makeMove")
    public ResponseEntity.BodyBuilder makeMove(@RequestHeader String token, ChessPieceMoveDTO chessPieceMoveDTO) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);
            User user = this.userService.findById(userId);

            if (user != null) {
                Lap tempLap = this.lapService.findByUserId(userId);
                List<ChessPieceMove> tempChessPieceMoves = tempLap.getGameHistory().getChessPieceMoves();

                if (this.gameHistoryService.checkLastMove(tempChessPieceMoves, userId)) {
                    ChessPieceMove chessPieceMove = this.chessPieceMoveFacade.DTOToChessPieceMove(chessPieceMoveDTO, user);

                    boolean result = this.gameHistoryService.add(tempLap.getGameHistory(), chessPieceMove);

                    if (result)
                        return ResponseEntity.ok();
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/play/checkMove")
    public ResponseEntity<Boolean> checkMove(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            if (userId != null && userId >= 1) {
                Lap tempLap = this.lapService.findByUserId(userId);
                List<ChessPieceMove> tempChessPieceMoves = tempLap.getGameHistory().getChessPieceMoves();

                boolean lastMoveResult = tempChessPieceMoves.get(tempChessPieceMoves.size() - 1).getUser().getIdUser().equals(userId);

                return ResponseEntity.ok(lastMoveResult);
            }
        }

        return ResponseEntity.ok(false);
    }

    @GetMapping("/play/getMove")
    public ResponseEntity<ChessPieceMoveDTO> getMove(@RequestHeader String token) {
        if (token != null) {
            Long userId = this.jwtTokenProvider.getUserIdFromToken(token);

            if (userId != null && userId >= 1) {
                Lap tempLap = this.lapService.findByUserId(userId);
                List<ChessPieceMove> tempChessPieceMoves = tempLap.getGameHistory().getChessPieceMoves();

                if (tempChessPieceMoves != null && !tempChessPieceMoves.isEmpty()) {
                    ChessPieceMove chessPieceMove = tempChessPieceMoves.get(tempChessPieceMoves.size() - 1);
                    ChessPieceMoveDTO chessPieceMoveDTO = this.chessPieceMoveFacade.chessPieceMoveToDTO(chessPieceMove);

                    return ResponseEntity.ok(chessPieceMoveDTO);
                }
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
