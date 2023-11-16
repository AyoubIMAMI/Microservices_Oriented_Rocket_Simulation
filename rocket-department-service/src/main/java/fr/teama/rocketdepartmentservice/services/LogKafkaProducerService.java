package fr.teama.rocketdepartmentservice.services;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.MissionLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogKafkaProducerService {
    private static KafkaTemplate<String, MissionLogDTO> kafkaTemplate;

    @Autowired
    public LogKafkaProducerService(KafkaTemplate<String, MissionLogDTO> kafkaTemplate) {
        LogKafkaProducerService.kafkaTemplate = kafkaTemplate;
    }

    public static void sendMissionLog(String serviceName, String text) {
        MissionLogDTO missionLogDTO = new MissionLogDTO(serviceName, text, LocalDateTime.now());
        kafkaTemplate.send("logs-topic", missionLogDTO);
    }
}

