package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Formato {
    @Id
    @NonNull
    private int id;
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "formato")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Libro> libros;

    @Override
    public String toString() {
        return nombre;
    }
}
