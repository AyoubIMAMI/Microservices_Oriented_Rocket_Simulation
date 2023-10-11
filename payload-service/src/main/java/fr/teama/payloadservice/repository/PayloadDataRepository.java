package fr.teama.payloadservice.repository;


import fr.teama.payloadservice.models.PayloadData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayloadDataRepository extends JpaRepository<PayloadData, Long> {
    List<PayloadData> findAllByRocketName(String rocketName);
}
