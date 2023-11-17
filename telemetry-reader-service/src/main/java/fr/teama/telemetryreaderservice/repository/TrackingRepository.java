package fr.teama.telemetryreaderservice.repository;


import fr.teama.telemetryreaderservice.models.Tracking;
import fr.teama.telemetryreaderservice.models.TrackingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long>{

    List<Tracking> findByCategory(TrackingCategory category);
}
