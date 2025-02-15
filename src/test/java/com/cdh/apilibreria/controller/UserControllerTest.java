package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class UserControllerLoginTest {


    @Autowired
    private LoginController loginController;

    @Autowired
    private UserRepository repository;

    @Test
    void login() {
        User user = repository.findAll().get(0);
        user.setPassword("1234");
        ResponseEntity<User> login = loginController.login(user);
        assertEquals(HttpStatus.OK, login.getStatusCode());
    }
}