package fr.teama.logsservice.repositories;

import fr.teama.logsservice.models.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long> {
}
