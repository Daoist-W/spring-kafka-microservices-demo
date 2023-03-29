package com.isikodon.kafka.stream;

import com.isikodon.kafka.schema.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    // bootstrap server
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    static final String UPSTREAM_TOPIC = "YouBuyyClickStreamData";
    static final String DOWNSTREAM_TOPIC = "CleansedYouBuyyClickStreamData";

    @Bean
    public NewTopic streamTopic(){
        return TopicBuilder.name(DOWNSTREAM_TOPIC)
                .build();
    }

    // declare props and configure to kStreamBuilder using this bean annotation
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "ClickStreamAnalysis");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        // declare Custom JsonSerde add trusted packages to deserializer
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, CustomerDataSerde.class);
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    // monitors the state of the stream builder
    public StreamsBuilderFactoryBeanConfigurer configurer() {
        return factoryBean -> factoryBean.setStateListener((newState, oldState) -> {
            log.info("State transition from " + oldState + " to " + newState);
        });
    }

    @Bean
    // declare KStream bean and implement filtering business logic
    public KStream<String, CustomerData> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, CustomerData> stream = kStreamBuilder.stream(UPSTREAM_TOPIC);
        stream
                .filter((s, customerData) -> customerData.getResponseTime() > 30)
                .to(DOWNSTREAM_TOPIC);
        return stream;
    }

}
