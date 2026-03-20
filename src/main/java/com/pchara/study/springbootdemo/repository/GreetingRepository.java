package com.pchara.study.springbootdemo.repository;

import com.pchara.study.springbootdemo.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {}
