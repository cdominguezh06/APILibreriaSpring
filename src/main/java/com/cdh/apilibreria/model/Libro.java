package com.cdh.apilibreria.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne(cascade = CascadeType.ALL)
    public Autor autor;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    public Temas tema;
    public double precio;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    public Edicion edicion;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    public Formato formato;

    public int cantidad;
}
