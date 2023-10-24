package fr.teama.telemetryservice.repository;

import fr.teama.telemetryservice.models.RobotData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotDataRepository extends JpaRepository<RobotData, Long> {
}
