package com.lodn.dockerjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {
    record Hello(String message, String system) {}

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<Hello> hello() {
        return new ResponseEntity<>(new Hello("Hello!!", System.getProperty("os.name")), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = usersRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
