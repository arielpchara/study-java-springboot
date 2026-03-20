package com.pchara.study.springbootdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pchara.study.springbootdemo.model.Note;
import com.pchara.study.springbootdemo.repository.NoteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "Endpoints for managing notes")
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    @Operation(summary = "List notes", description = "Returns all saved notes from the database")
    @ApiResponse(responseCode = "200", description = "List of notes")
    public List<Note> listNotes() {
        return this.noteRepository.findAll();
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get note by slug", description = "Returns a single note identified by its slug")
    @ApiResponse(responseCode = "200", description = "Note found")
    @ApiResponse(responseCode = "404", description = "Note not found")
    public Note getNoteBySlug(String slug) {
        return this.noteRepository.findBySlug(slug);
    }

    @PostMapping("/")
    @Operation(summary = "Create note", description = "Saves a new note to the database")
    @ApiResponse(responseCode = "201", description = "Note created")
    public Note createNote(String title, String content) {
        Note note = new Note(title, content);
        return this.noteRepository.save(note);
    }
    
}
