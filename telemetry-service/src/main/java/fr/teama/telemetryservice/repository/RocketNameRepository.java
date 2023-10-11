package fr.teama.telemetryservice.repository;

import fr.teama.telemetryservice.models.RocketName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketNameRepository extends JpaRepository<RocketName, Long> {

    RocketName findTopByOrderByIdDesc();
}
