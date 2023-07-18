package org.project.chess_online.service;

import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.project.chess_online.repository.LapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LapService {
    private final LapRepository lapRepository;

    @Autowired
    public LapService(LapRepository lapRepository) {
        this.lapRepository = lapRepository;
    }

    public Lap findById(Long id) {
        return this.lapRepository.findById(id).get(); // orElseGet(Lap::new);
    }

    public Lap findByUsers(User userFirst, User userSecond) {
        return this.lapRepository.findByUsers(userFirst, userSecond); // orElseGet(Lap::new);
    }

    public void create(User userFirst, User userSecond) {
        Lap tempLap = new Lap();
        tempLap.setUserFirst(userFirst);
        tempLap.setUserSecond(userSecond);
        tempLap.setActive(true);

        this.lapRepository.save(tempLap);
    }
}
