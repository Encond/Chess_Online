package org.project.chess_online.repository;

import org.project.chess_online.entity.Chat;
import org.project.chess_online.entity.Lap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "from Chat c where c.lap.idLap = ?1")
    Chat findByLapId(Long lapId);
}
