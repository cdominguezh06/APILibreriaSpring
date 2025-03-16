package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.BuyOrderDTO;
import com.cdh.apilibreria.services.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BuyOrderController {

    private final BuyOrderService service;

    @Autowired
    public BuyOrderController(BuyOrderService service) {
        this.service = service;
    }

    @GetMapping("api/orders/find")
    public ResponseEntity<List<BuyOrderDTO>> get() {
        return service.get();
    }

    @PostMapping("api/orders/new")
    public ResponseEntity<BuyOrderDTO> post(@RequestBody BuyOrderDTO buyOrderDTO) {
        return service.post(buyOrderDTO);
    }

    @PutMapping("api/orders/update")
    public ResponseEntity<BuyOrderDTO> put(@RequestBody BuyOrderDTO buyOrderDTO) {
        return service.put(buyOrderDTO);
    }

    @DeleteMapping("api/orders/delete")
    public ResponseEntity<BuyOrderDTO> delete(@RequestParam Long id) {
        return service.delete(id);
    }
}
