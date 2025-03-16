package com.cdh.apilibreria.model.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LibroDTOMapper.class})
public interface BuyOrderItemDTOMapper {
}
