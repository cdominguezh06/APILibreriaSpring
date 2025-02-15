package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.TemaDTO;
import com.cdh.apilibreria.model.entities.Temas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemaDTOMapper {
     TemaDTO toTemaDTO(Temas tema);
     Temas toTema(TemaDTO temaDTO);
}
