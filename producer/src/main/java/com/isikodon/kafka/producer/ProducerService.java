package com.isikodon.kafka.producer;

import com.isikodon.kafka.schema.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Slf4j
@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, CustomerData> template;

    private static final String TOPIC = "YouBuyyClickStreamData";
    @Value("${clickstream-data}")
    private String path;

    public String sendMessage(){
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                CustomerData data = new CustomerData(line);
                template.send(TOPIC, data);
                log.info("Sent {}", data.getCustomerId());
                counter++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        log.info("sending complete");
        return String.format("sent %s messages", counter);
    }
}
