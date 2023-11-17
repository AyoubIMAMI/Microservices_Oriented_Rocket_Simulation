package fr.teama.telemetryreaderservice.repository;

import fr.teama.telemetryreaderservice.models.RobotData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotDataRepository extends JpaRepository<RobotData, Long> {
}
