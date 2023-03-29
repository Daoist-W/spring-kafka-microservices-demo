package com.isikodon.kafka.consumer;

import com.isikodon.kafka.schema.CustomerData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AppKafkaListener {

    static final String UPSTREAM_TOPIC = "CleansedYouBuyyClickStreamData";
    @KafkaListener(
            topics = UPSTREAM_TOPIC,
            groupId = "ClickStreamConsumer-1",
            containerFactory = "factory" // must be added or will default to in-built factory
    )
    public void listen(CustomerData data){
        System.out.println("Consumed data: " + data + "😊");
    }
}
