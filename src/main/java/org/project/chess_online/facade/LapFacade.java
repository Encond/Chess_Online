package org.project.chess_online.facade;

import org.project.chess_online.dto.LapDTO;
import org.project.chess_online.entity.Lap;
import org.springframework.stereotype.Component;

@Component
public class LapFacade {
    public LapDTO lapToLapDTO(Lap lap) {
        LapDTO lapDTO = new LapDTO();
        lapDTO.setId(lap.getIdLap());

        lapDTO.setUserIdFirst(lap.getUserFirst().getIdUser());
        lapDTO.setUserUsernameFirst(lap.getUserFirst().getUsername());

        lapDTO.setUserIdSecond(lap.getUserSecond().getIdUser());
        lapDTO.setUserUsernameSecond(lap.getUserSecond().getUsername());

        lapDTO.setActive(true);
        lapDTO.setChatId(lap.getChat().getIdChat());

        return lapDTO;
    }
}
