package fr.teama.telemetryreaderservice.repository;

import fr.teama.telemetryreaderservice.models.StageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDataRepository extends JpaRepository<StageData, Long> {
}
