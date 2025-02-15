package com.cdh.apilibreria.model.DTO;


import com.cdh.apilibreria.model.DireccionUsuario;

public record UserDTO(String username, String email, String nombre, String apellidos, DireccionUsuario direccion, String role) {
}
