package com.pchara.study.springbootdemo.controller;

import com.pchara.study.springbootdemo.model.Greeting;
import com.pchara.study.springbootdemo.repository.GreetingRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Demo", description = "Sample endpoints for the Spring Boot demo project")
public class WelcomeController {

    private final GreetingRepository greetingRepository;

    public WelcomeController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping("/hello")
    @Operation(summary = "Health check", description = "Returns a welcome message and status ok")
    @ApiResponse(responseCode = "200", description = "Successful response",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(example = "{\"message\":\"Welcome to the Spring Boot demo project\",\"status\":\"ok\"}")))
    public Map<String, String> hello() {
        return Map.of(
                "message", "Welcome to the Spring Boot demo project",
                "status", "ok"
        );
    }

    @GetMapping("/greetings")
    @Operation(summary = "List greetings", description = "Returns all saved greetings from the database")
    @ApiResponse(responseCode = "200", description = "List of greetings",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Greeting.class)))
    public List<Greeting> listGreetings() {
        return greetingRepository.findAll();
    }

    @PostMapping("/greetings")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create greeting", description = "Saves a new greeting message to the database")
    @ApiResponse(responseCode = "201", description = "Greeting created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Greeting.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public Greeting createGreeting(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Greeting payload",
                    required = true,
                    content = @Content(schema = @Schema(example = "{\"message\":\"Hello, World!\"}")))
            Map<String, String> body) {
        return greetingRepository.save(new Greeting(body.get("message")));
    }
}
