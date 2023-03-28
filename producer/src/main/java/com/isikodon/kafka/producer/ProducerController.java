package com.isikodon.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producer")
public class ProducerController {

    @Autowired
    private ProducerService service;

    @GetMapping
    public void sendMessages(){
        service.sendMessage();
    }
}
