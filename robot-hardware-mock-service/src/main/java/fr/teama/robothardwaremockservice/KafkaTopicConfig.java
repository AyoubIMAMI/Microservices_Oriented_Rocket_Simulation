package fr.teama.robothardwaremockservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // Define your Kafka topic name and partitions
    private static final String TOPIC_NAME = "robot-hardware-topic";
    private static final String SAMPLE_TOPIC_NAME = "robot-hardware-sample-topic";
    private static final int PARTITIONS = 1;

    // Create a bean for the Kafka topic
    @Bean
    public NewTopic createRobotHardwareTopic() {
        return TopicBuilder.name(TOPIC_NAME)
                .partitions(PARTITIONS)
                .replicas(1) // Set the number of replicas for fault tolerance
                .build();
    }

    @Bean
    public NewTopic createRobotHardwareSampleTopic() {
        return TopicBuilder.name(SAMPLE_TOPIC_NAME)
                .partitions(PARTITIONS)
                .replicas(1)
                .build();
    }
}