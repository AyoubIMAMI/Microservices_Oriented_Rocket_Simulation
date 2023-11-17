package fr.teama.robotdepartmentservice.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private static final int PARTITIONS = 1;

    // Create a bean for the Kafka topic

    @Bean
    public NewTopic createRobotEventTopic() {
        return TopicBuilder.name("robot-event-topic")
                .partitions(PARTITIONS)
                .replicas(1) // Set the number of replicas for fault tolerance
                .build();
    }
}