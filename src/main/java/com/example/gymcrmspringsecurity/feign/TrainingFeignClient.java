package com.example.gymcrmspringsecurity.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "training-feign-client", url = "${training.feign.client.url}")
public interface TrainingFeignClient {

    @GetMapping("/trainings/status-check")
    ResponseEntity<String> sayHello();
}