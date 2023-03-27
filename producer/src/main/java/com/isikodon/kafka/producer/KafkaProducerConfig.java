package com.isikodon.kafka.producer;

import com.isikodon.kafka.schema.CustomerData;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    // bootstrap server
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // config map
    public Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "ClickStreamProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
         props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    // producer factory
    @Bean
    public ProducerFactory<String, CustomerData> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // kafka template
    @Bean
    public KafkaTemplate<String, CustomerData> kafkaTemplate(ProducerFactory<String, CustomerData> factory){
        return new KafkaTemplate<>(factory);
    }

}
