package test.project4v2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoggingController {
    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @PostMapping("/test")
    public String handleRequest(@RequestBody String body) {
        logger.info("Received request body: {}", body);
        return "Request received";
    }
    @GetMapping("/getTest")
    public String handleGetRequest() {
        logger.info("Received GET request");
        return "GET request received";
    }
}