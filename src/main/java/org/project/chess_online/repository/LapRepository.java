package org.project.chess_online.repository;

import org.project.chess_online.entity.Lap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LapRepository extends JpaRepository<Lap, Long> {
}
