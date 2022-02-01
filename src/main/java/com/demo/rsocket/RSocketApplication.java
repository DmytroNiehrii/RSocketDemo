package com.demo.rsocket;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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

    @MessageExceptionHandler
    String handleException(Exception e) {
        var message = "there's been a problem! " + NestedExceptionUtils.getMostSpecificCause(e);
        System.out.println("error: " + e);
        return message;
    }

    @MessageMapping("greetings.{name}")
    Flux<GreetingResponse> greet(@DestinationVariable String name) {
        Assert.isTrue(Character.isUpperCase(name.charAt(0)), () -> "the name should start with a capital letter!");
        return Flux
            .fromStream(Stream.generate(() -> new GreetingResponse(String.format("Hello %s @ %s", name, Instant.now()))))
            .take(3)
            .delayElements(Duration.ofSeconds(1));
    }

    @MessageMapping("greetings-single")
    GreetingResponse greetSingle(GreetingRequest request) {
        Assert.isTrue(Character.isUpperCase(request.name().charAt(0)), () -> "the name should start with a capital letter!");
        return new GreetingResponse(String.format("Hello %s", request.name()));
    }
}