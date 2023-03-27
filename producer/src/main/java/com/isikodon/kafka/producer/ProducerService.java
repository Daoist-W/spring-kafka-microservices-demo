package com.isikodon.kafka.producer;

import com.isikodon.kafka.schema.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, CustomerData> template;

    private static final String TOPIC = "YouBuyyClickStreamData";

    public void sendMessage(){
        try (BufferedReader br = new BufferedReader(new FileReader("producer/src/main/resources/ClickStreamMasterData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                CustomerData data = new CustomerData(line);
                template.send(TOPIC, data);
                log.info("Sent {}", data.getCustomerId());
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        log.info("sending complete");
    }
}
