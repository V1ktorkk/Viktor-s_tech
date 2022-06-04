package ru.itmo.kotikilab5service.service;

import org.springframework.kafka.annotation.KafkaListener;

public class ConsumeService {
    @KafkaListener(id = "Black", topics = {"Kotiki"}, containerFactory = "singleFactory")
    public void consume(String str) {
        System.out.println("hdfhd");

    }
}
