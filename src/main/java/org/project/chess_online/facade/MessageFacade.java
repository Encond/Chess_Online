package org.project.chess_online.facade;

import org.project.chess_online.dto.MessageDTO;
import org.project.chess_online.entity.Message;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageFacade {
    public List<MessageDTO> messageToMessageDTO(List<Message> messages, boolean color) {
        List<MessageDTO> messageDTOS = new ArrayList<MessageDTO>();

        messages.forEach(message -> {
            MessageDTO tempMessageDTO = new MessageDTO();
            tempMessageDTO.setColor(color);
            tempMessageDTO.setText(message.getText());

            messageDTOS.add(tempMessageDTO);
        });

        return messageDTOS;
    }
}
