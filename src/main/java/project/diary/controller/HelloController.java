package project.diary.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PersistenceContext
    EntityManager em;

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }
}
