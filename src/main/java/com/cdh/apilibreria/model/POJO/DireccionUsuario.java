package com.cdh.apilibreria.model.POJO;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Embeddable
public class DireccionUsuario {
    @NonNull
    private String pais;
    @NonNull
    private String estado;
    @NonNull
    private String ciudad;
    @NonNull
    private String calle;
    @NonNull
    private String numero;
    @NonNull
    private String codigoPostal;
}
