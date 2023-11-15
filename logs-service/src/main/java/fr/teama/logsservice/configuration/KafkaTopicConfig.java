package fr.teama.logsservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // Define your Kafka topic name and partitions
    private static final String TOPIC_NAME = "logs-topic";
    private static final int PARTITIONS = 3;

    // Create a bean for the Kafka topic
    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(TOPIC_NAME)
                .partitions(PARTITIONS)
                .replicas(1) // Set the number of replicas for fault tolerance
                .build();
    }
}