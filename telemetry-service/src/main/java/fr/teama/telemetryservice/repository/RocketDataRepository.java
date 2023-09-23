package fr.teama.telemetryservice.repository;

import fr.teama.telemetryservice.models.RocketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketDataRepository extends JpaRepository<RocketData, Long> {
}
