package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Temas {
    @Id
    @NonNull
    private int id;
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "tema")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Libro> libros;

    @Override
    public String toString() {
        return nombre;
    }
}
