package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> login(User user){
        if(userRepository.existsById(user.getUsername())){
            User found = userRepository.getReferenceById(user.getUsername());
            String encriptada = encryptPassword(user.getPassword());
            System.out.println(encriptada);
            System.out.println(found.getPassword());
            if(found.getPassword().equals(encriptada)){
                return ResponseEntity.ok(found);
            }
            return ResponseEntity.badRequest().eTag("Contraseña incorrecta").build();
        }
        return ResponseEntity.notFound().eTag("Usuario no existente").build();
    }

    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
    }

    public List<User> get() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> post(User user) {
        if (userRepository.existsByEmail(user.getUsername())) {
            return ResponseEntity.badRequest().eTag("El correo ya está en uso").build();
        }
        if(userRepository.existsById(user.getUsername())){
            return ResponseEntity.badRequest().eTag("El nombre de usuario ya está en uso").build();
        }
        user.setPassword(encryptPassword(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> put(User user) {
        if (userRepository.existsById(user.getUsername())) {
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<User> delete(String id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getReferenceById(id);
            userRepository.delete(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
