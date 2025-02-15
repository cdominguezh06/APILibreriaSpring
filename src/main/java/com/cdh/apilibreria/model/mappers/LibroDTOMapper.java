package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.LibroDTO;
import com.cdh.apilibreria.model.entities.Libro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AutorDTOMapper.class, EdicionDTOMapper.class, TemaDTOMapper.class, FormatoDTOMapper.class})
public interface LibroDTOMapper{
    
    LibroDTO toLibroDTO(Libro libro);
    Libro toLibro(LibroDTO libroDTO);
}
