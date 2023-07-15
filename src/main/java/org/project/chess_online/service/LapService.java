package org.project.chess_online.service;

import org.project.chess_online.entity.Lap;
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

    private List<Lap> getAll() {
        return this.lapRepository.findAll();
    }

    private Lap findById(Long id) {
        return this.lapRepository.findById(id).get(); // orElseGet(Lap::new);
    }
}
