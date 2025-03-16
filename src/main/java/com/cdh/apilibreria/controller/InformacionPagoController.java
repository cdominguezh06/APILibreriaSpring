package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.InformacionPagoDTO;
import com.cdh.apilibreria.services.InformacionPagoService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class InformacionPagoController implements GenericController<InformacionPagoDTO, String> {

    private final InformacionPagoService informacionPagoService;

    @Autowired
    public InformacionPagoController(InformacionPagoService informacionPagoService) {
        this.informacionPagoService = informacionPagoService;
    }

    @Override
    @GetMapping("/api/payments")
    public ResponseEntity<List<InformacionPagoDTO>> get() {
        return informacionPagoService.get();
    }

    @Override
    @PostMapping("/api/payments")
    public ResponseEntity<InformacionPagoDTO> post(InformacionPagoDTO informacionPagoDTO) {
        return informacionPagoService.post(informacionPagoDTO);
    }

    @Override
    @PutMapping("/api/payments")
    @Transactional
    public ResponseEntity<InformacionPagoDTO> put(InformacionPagoDTO informacionPagoDTO) {
        return informacionPagoService.put(informacionPagoDTO);
    }

    @Override
    @DeleteMapping("/api/payments")
    public ResponseEntity<InformacionPagoDTO> delete(String id) {
        return informacionPagoService.delete(id);
    }

    @PostMapping("/api/users/{userId}/payments/{numeroTarjeta}")
    @Transactional
    public ResponseEntity<InformacionPagoDTO> addInformacionPagoToUser(@PathVariable String userId, @PathVariable String numeroTarjeta) {
        return informacionPagoService.addInformacionPagoToUser(userId, numeroTarjeta);
    }

    @GetMapping("/api/users/{userId}/payments")
    public ResponseEntity<List<InformacionPagoDTO>> getInformacionPagoByUser(@PathVariable String userId) {
        return informacionPagoService.getInformacionPagoByUser(userId);
    }
}
