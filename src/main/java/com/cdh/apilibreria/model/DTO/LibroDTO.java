package com.cdh.apilibreria.model.DTO;

public record LibroDTO(String ISBN, String nombre, AutorDTO autor, FormatoDTO formato, EdicionDTO edicion, TemaDTO tema, int cantidad, double precio, String imgName) {
}
