package com.isikodon.kafka.consumer;

import com.isikodon.kafka.schema.CustomerData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    // bootstrap server
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // config map
    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "ClickStreamConsumer");
        return props;
    }

    // consumer factory
    @Bean
    public ConsumerFactory<String, CustomerData> consumerFactory(){
        // customise the serializer to add trusted packages
        JsonDeserializer<CustomerData> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("com.isikodon.kafka.schema");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }


    // kafka listener factory
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CustomerData>> factory(
            ConsumerFactory<String, CustomerData> consumerFactory
    ){
        var concurrentFactory = new ConcurrentKafkaListenerContainerFactory<String, CustomerData>();
        concurrentFactory.setConsumerFactory(consumerFactory);
        return concurrentFactory;
    }

}
