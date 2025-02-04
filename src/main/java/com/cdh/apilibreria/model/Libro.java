package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {
    @Id
    public int id;
    public String nombre;
    @NonNull
    @JsonProperty("ISBN")
    public String ISBN;

    public String imgName;
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id")
    public Autor autor;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tema_id")
    public Temas tema;

    public double precio;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "edicion_id")
    public Edicion edicion;

    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "formato_id")
    public Formato formato;

    public int cantidad;
}
