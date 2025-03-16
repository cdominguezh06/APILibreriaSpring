package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.UserDTO;
import com.cdh.apilibreria.model.entities.InformacionPago;
import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.model.mappers.UserDTOMapper;
import com.cdh.apilibreria.model.mappers.UserDTOMapperImpl;
import com.cdh.apilibreria.model.repository.InformacionPagoRepository;
import com.cdh.apilibreria.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final InformacionPagoRepository informacionPagoRepository;
    @Qualifier("userDTOMapperImpl")
    private final UserDTOMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, InformacionPagoRepository informacionPagoRepository, UserDTOMapperImpl mapper) {
        this.userRepository = userRepository;
        this.informacionPagoRepository = informacionPagoRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<UserDTO> login(UserDTO user){
        if(userRepository.existsById(user.username())){
            User found = userRepository.getReferenceById(user.username());
            String encriptada = encryptPassword(user.password());
            System.out.println(encriptada);
            System.out.println(found.getPassword());
            if(found.getPassword().equals(encriptada)){
                return ResponseEntity.ok(mapper.toUserDTO(found));
            }
            return ResponseEntity.badRequest().eTag("Contraseña incorrecta").build();
        }
        return ResponseEntity.notFound().eTag("Usuario no existente").build();
    }

    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
    }

    public List<UserDTO> get() {
        return userRepository.findAll().stream()
                .map(mapper::toUserDTO)
                .toList();
    }

    public ResponseEntity<UserDTO> post(UserDTO user) {
        if (userRepository.existsByEmail(user.username())) {
            return ResponseEntity.badRequest().eTag("El correo ya está en uso").build();
        }
        if(userRepository.existsById(user.username())){
            return ResponseEntity.badRequest().eTag("El nombre de usuario ya está en uso").build();
        }
        User toSave = mapper.toUser(user);
        toSave.setPassword(encryptPassword(user.password()));
        userRepository.save(toSave);
        return ResponseEntity.ok(mapper.toUserDTO(toSave));
    }

    public ResponseEntity<UserDTO> put(UserDTO user) {
        if (userRepository.existsById(user.username())) {
            userRepository.save(mapper.toUser(user));
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UserDTO> delete(String id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getReferenceById(id);
            userRepository.delete(user);
            return ResponseEntity.ok(mapper.toUserDTO(user));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<UserDTO>> getUserByInformacionPago(String numeroTarjeta) {
        if(!informacionPagoRepository.existsById(numeroTarjeta)){
            return ResponseEntity.notFound().build();
        }
        InformacionPago informacionPago = informacionPagoRepository.getReferenceById(numeroTarjeta);
        return ResponseEntity.ok(informacionPago.getUsuarios().stream()
                .map(mapper::toUserDTO)
                .toList());
    }
}
