package com.pchara.study.springbootdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "greetings")
@Schema(description = "A persisted greeting message")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "The greeting text", example = "Hello, World!")
    private String message;

    @Column(nullable = false, updatable = false)
    @Schema(description = "Timestamp when the greeting was created", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();

    protected Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }
}
