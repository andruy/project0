package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation is used to tell Spring that this class is a controller
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class); // This is the name of the field that will be injected
    //migrate log to logger

    @PostMapping    // This annotation tells Spring that this method will be mapped to the /message endpoint
    public ResponseEntity<String> receiveMessage(@RequestBody String message) { // This annotation tells Spring that this method will accept a String object as a parameter
        MDC.put("event", "message-submit"); // This method will add a key-value pair to the MDC
        log.info("{}, {}, {}, {}", message, "String one", "String two", "String three"); // This method will log the message to the console
        log.info("{}", message);
        log.info(message);
        log.warn(message, new RuntimeException("Something went wrong!"));   // This method will log the message to the console and throw an exceptions
        MDC.clear(); // This method will clear the MDC

        return ResponseEntity.ok("Message received and logged");    // This method will return an HTTP response with status code 200
    }
}
