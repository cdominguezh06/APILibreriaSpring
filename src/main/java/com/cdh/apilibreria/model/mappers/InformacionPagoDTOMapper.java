package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.InformacionPagoDTO;
import com.cdh.apilibreria.model.entities.InformacionPago;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InformacionPagoDTOMapper {

    InformacionPago mapToEntity(InformacionPagoDTO informacionPagoDTO);
    InformacionPagoDTO mapToDTO(InformacionPago informacionPago);
}
