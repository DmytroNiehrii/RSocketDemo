package com.demo.rsocket;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class RSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSocketApplication.class, args);
    }
}

record GreetingRequest(String name) {}
record GreetingResponse(String message) {}

@Controller
class GreetingsRSocketController {

    @MessageMapping("greetings.{name}")
    Flux<GreetingResponse> greet(@DestinationVariable String name) {
        return Flux
            .fromStream(Stream.generate(() -> new GreetingResponse(String.format("Hello %s @ %s", name, Instant.now()))))
            .take(10)
            .delayElements(Duration.ofSeconds(1));
    }
}