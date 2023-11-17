package fr.teama.telemetryreaderservice.repository;

import fr.teama.telemetryreaderservice.models.RocketName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketNameRepository extends JpaRepository<RocketName, Long> {

    RocketName findTopByOrderByIdDesc();
}
