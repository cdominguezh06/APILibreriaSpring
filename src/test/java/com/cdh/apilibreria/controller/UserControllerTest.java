package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.UserDTO;
import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.model.mappers.UserDTOMapper;
import com.cdh.apilibreria.model.repository.UserRepository;
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

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Test
    void login() {
        User user = repository.findAll().get(0);
        user.setPassword("1234");
        ResponseEntity<UserDTO> login = loginController.login(userDTOMapper.toUserDTO(user));
        assertEquals(HttpStatus.OK, login.getStatusCode());
    }
}