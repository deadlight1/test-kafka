package com.volodymyr.pletnov.testkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestKafkaApplication.class, args);
    }

}
