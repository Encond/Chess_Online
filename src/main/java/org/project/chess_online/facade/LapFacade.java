package org.project.chess_online.facade;

import org.project.chess_online.dto.LapDTO;
import org.project.chess_online.entity.Lap;
import org.springframework.stereotype.Component;

@Component
public class LapFacade {
    public LapDTO lapToLapDTO(Lap lap, Long currentUserId) {
        LapDTO lapDTO = new LapDTO();

        String tempEnemyName = currentUserId.equals(lap.getUserFirst().getIdUser()) ? lap.getUserSecond().getUsername() : lap.getUserFirst().getUsername();

        lapDTO.setEnemyName(tempEnemyName);
        lapDTO.setChessPiecesColor(currentUserId.equals(lap.getUserFirst().getIdUser()));

        return lapDTO;
    }
}
