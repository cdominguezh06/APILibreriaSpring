package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.FormatoDTO;
import com.cdh.apilibreria.model.entities.Formato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormatoDTOMapper {
    FormatoDTO toFormatoDTO(Formato formato);
    Formato toFormato(FormatoDTO formatoDTO);
}
