package com.cdh.apilibreria.model.entities;

import com.cdh.apilibreria.model.DireccionUsuario;
import com.cdh.apilibreria.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @NonNull
    private String username;
    @NonNull
    @Column(unique = true)
    private String email;
    private String nombre;
    private String apellidos;
    @Embedded
    private DireccionUsuario direccion;
    @NonNull
    @JsonIgnore
    private String password;

    private UserRole role;

    public User(@NonNull String username, @NonNull String email, @NonNull String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = UserRole.CLIENT;
    }
}
