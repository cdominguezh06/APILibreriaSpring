package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @RequestMapping(path = "/api/users/login")
    public ResponseEntity<User> login(@RequestBody User user){
        return userService.login(user);
    }

}
