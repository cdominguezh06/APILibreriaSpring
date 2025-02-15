package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.EdicionDTO;
import com.cdh.apilibreria.model.entities.Edicion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EdicionDTOMapper {
    EdicionDTO toEdicionDTO(Edicion edicion);
    Edicion toEdicion(EdicionDTO edicionDTO);
}
