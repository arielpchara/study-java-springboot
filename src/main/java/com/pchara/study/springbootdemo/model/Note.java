package com.pchara.study.springbootdemo.model;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
@Schema(description = "A simple note with a title and content")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Auto-generated unique ID", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;


    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "A URL-friendly slug generated from the title", example = "meeting-notes", accessMode = Schema.AccessMode.READ_ONLY)
    private String slug;

    @Column(nullable = false, updatable = false)
    @Schema(description = "The title of the note", example = "Meeting Notes")
    private String title;

    @Schema(description = "The content of the note", example = "Discuss project milestones and deadlines.")
    private String content;

    @Schema(description = "Timestamp when the note was created", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt = Instant.now();

    public Note() {
    }

    public Note(String title) {
        this.title = title;
        this.slug = generateSlug(title);
    }

    public Note(String title, String content) {
        this.title = title;
        this.slug = generateSlug(title);
        this.content = content;
    }

    private String generateSlug(String title) {
        return title.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
