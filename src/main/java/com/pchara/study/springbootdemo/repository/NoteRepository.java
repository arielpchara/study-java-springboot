package com.pchara.study.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pchara.study.springbootdemo.model.Note;

public interface NoteRepository extends JpaRepository<Note, String> {
    Note findBySlug(String slug); // custom query method to find a note by its slug
}