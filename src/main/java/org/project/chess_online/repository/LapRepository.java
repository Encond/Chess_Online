package org.project.chess_online.repository;

import org.project.chess_online.entity.Lap;
import org.project.chess_online.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LapRepository extends JpaRepository<Lap, Long> {
    @Query("from Lap l where l.userFirst = ?1 and l.userSecond = ?2 or l.userFirst = ?2 and l.userSecond = ?1")
    Lap findByUsers(User userFirst, User userSecond);
}
