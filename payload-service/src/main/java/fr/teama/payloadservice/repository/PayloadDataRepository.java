package fr.teama.payloadservice.repository;


import fr.teama.payloadservice.entities.PayloadData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadDataRepository extends JpaRepository<PayloadData, Long>{
}
