package com.cdh.apilibreria.model.DTO;


import com.cdh.apilibreria.model.POJO.DireccionUsuario;

public record UserDTO(String username, String password, String email, String nombre, String apellidos, DireccionUsuario direccion, int role) {
}
