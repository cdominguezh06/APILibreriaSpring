package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.AutorDTO;
import com.cdh.apilibreria.model.entities.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorDTOMapper {
    AutorDTO toAutorDTO(Autor autor);
    Autor toAutor(AutorDTO autorDTO);
}
