package com.cdh.apilibreria.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class InformacionPago {
    @NonNull
    @Id
    private String numeroTarjeta;
    @NonNull
    private String fechaExpiracion;
    @NonNull
    private String CVV;
    @NonNull
    private String nombreTitular;

    @ManyToMany(mappedBy = "informacionesPago")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<User> usuarios;

    public void addUser(User user) {
        usuarios.add(user);
    }
}
