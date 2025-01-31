package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Autor {
    @Id
    @NonNull
    private int id;
    @NonNull
    private String nombre;
    @OneToMany(mappedBy = "autor")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Libro> libros;

    @Override
    public String toString() {
        return nombre;
    }
}
