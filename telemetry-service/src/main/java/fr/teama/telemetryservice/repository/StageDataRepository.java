package fr.teama.telemetryservice.repository;

import fr.teama.telemetryservice.models.StageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDataRepository extends JpaRepository<StageData, Long> {
}
