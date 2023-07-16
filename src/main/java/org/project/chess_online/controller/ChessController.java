package org.project.chess_online.controller;

import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
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
    private final UserService userService;
    private List<User> userQueue;

    @Autowired
    public ChessController(LapService lapService, UserService userService) {
        this.lapService = lapService;
        this.userService = userService;

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

    @GetMapping("/play/{lapId}")
    public ResponseEntity<Lap> playGame(@PathVariable("lapId") Long lapId) {
        Lap tempLap = this.lapService.findById(lapId);

        return new ResponseEntity<>(tempLap, HttpStatus.OK);
    }
}
