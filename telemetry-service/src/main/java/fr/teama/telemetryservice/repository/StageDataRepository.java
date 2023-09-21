package fr.teama.telemetryservice.repository;

import fr.teama.telemetryservice.entities.StageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageDataRepository extends JpaRepository<StageData, Long> {
}
