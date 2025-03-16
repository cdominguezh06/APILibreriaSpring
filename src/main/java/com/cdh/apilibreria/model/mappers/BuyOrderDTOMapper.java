package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.BuyOrderDTO;
import com.cdh.apilibreria.model.entities.BuyOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LibroDTOMapper.class, UserDTOMapper.class})
public interface BuyOrderDTOMapper {

    BuyOrderDTO toBuyOrderDTO(BuyOrder buyOrder);
    BuyOrder toBuyOrder(BuyOrderDTO buyOrderDTO);

}
