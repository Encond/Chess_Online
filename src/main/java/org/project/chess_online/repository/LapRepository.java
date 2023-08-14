package org.project.chess_online.repository;

import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LapRepository extends JpaRepository<Lap, Long> {
    @Query("from Lap l where l.userFirst.idUser = ?1 and l.userSecond.idUser = ?2 or l.userFirst.idUser = ?2 and l.userSecond.idUser = ?1")
    Lap findByUsersId(Long userFirstId, Long userSecondId);

    @Query(value = "from Lap where userFirst.idUser = ?1 or userSecond.idUser = ?1")
    Lap findByUserId(Long userId);

    @Query(value = "from Lap l where l.idLap = ?1")
    Lap findLapById(Long lapId);
}
