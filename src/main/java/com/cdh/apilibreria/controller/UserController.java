package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.UserDTO;
import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.services.UserService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController implements GenericController<UserDTO, String> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> get() {
        return ResponseEntity.ok(userService.get());
    }
    @Override
    @PostMapping("/api/users")
    public ResponseEntity<UserDTO> post(@RequestBody UserDTO user) {
       return userService.post(user);
    }

    @Transactional
    @Override
    @PutMapping("/api/users")
    public ResponseEntity<UserDTO> put(@RequestBody UserDTO user) {
        return userService.put(user);
    }

    @Override
    @DeleteMapping("/api/users")
    public ResponseEntity<UserDTO> delete(@RequestParam String id) {
        return userService.delete(id);
    }


}
