package fr.teama.telemetryservice.configuration;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig
{
    public static final String GROUP_ID = "group_id";

    @Bean
    public ConsumerFactory<String, RocketDataDTO> consumerRocketDataFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "host.docker.internal:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, RocketDataDTO.class);
        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(RocketDataDTO.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RocketDataDTO> messageRocketDataListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, RocketDataDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerRocketDataFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, StageDataDTO> consumerStageDataFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "host.docker.internal:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, StageDataDTO.class);
        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(StageDataDTO.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StageDataDTO> messageStageDataListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, StageDataDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerStageDataFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, RobotDataDTO> consumerRobotDataFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "host.docker.internal:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, RobotDataDTO.class);
        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(RobotDataDTO.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RobotDataDTO> messageRobotDataListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, RobotDataDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerRobotDataFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PayloadDataDTO> consumerPayloadDataFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "host.docker.internal:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PayloadDataDTO.class);
        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(PayloadDataDTO.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PayloadDataDTO> messagePayloadDataListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, PayloadDataDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerPayloadDataFactory());
        return factory;
    }
}
