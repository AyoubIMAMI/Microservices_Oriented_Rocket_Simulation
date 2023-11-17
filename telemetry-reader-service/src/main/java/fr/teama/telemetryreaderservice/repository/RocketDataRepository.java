package fr.teama.telemetryreaderservice.repository;

import fr.teama.telemetryreaderservice.models.RocketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketDataRepository extends JpaRepository<RocketData, Long> {
}
