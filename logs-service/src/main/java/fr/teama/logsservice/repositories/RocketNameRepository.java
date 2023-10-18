package fr.teama.logsservice.repositories;

import fr.teama.logsservice.models.RocketName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketNameRepository extends JpaRepository<RocketName, Long> {

    RocketName findTopByOrderByIdDesc();
}
