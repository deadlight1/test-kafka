package com.volodymyr.pletnov.testkafka.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volodymyr.pletnov.testkafka.dto.StarshipDto;
import com.volodymyr.pletnov.testkafka.service.StarshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;


    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    @Override
    public void produce() {
        StarshipDto dto = createDto();
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    private StarshipDto createDto() {
        return new StarshipDto("Starship " + (LocalTime.now().toNanoOfDay() / 1000000));
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
