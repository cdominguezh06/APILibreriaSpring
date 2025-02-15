package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.services.UserService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController implements GenericController<User, String> {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.ok(userService.get());
    }
    @Override
    @PostMapping("/api/users")
    public ResponseEntity<User> post(@RequestBody User user) {
       return userService.post(user);
    }

    @Override
    @PutMapping("/api/users")
    public ResponseEntity<User> put(@RequestBody User user) {
        return userService.put(user);
    }

    @Override
    @DeleteMapping("/api/users")
    public ResponseEntity<User> delete(@RequestParam String id) {
        return userService.delete(id);
    }


}
