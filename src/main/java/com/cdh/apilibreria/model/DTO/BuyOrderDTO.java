package com.cdh.apilibreria.model.DTO;

import java.util.List;

public record BuyOrderDTO(Long id, List<BuyOrderItemDTO> buyOrderItems, UserDTO user) {
}
