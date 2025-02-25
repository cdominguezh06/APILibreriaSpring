package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.EdicionDTO;
import com.cdh.apilibreria.model.entities.Edicion;
import com.cdh.apilibreria.repository.EdicionesRepository;
import com.cdh.apilibreria.services.EdicionesService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class EdicionesController implements GenericController<EdicionDTO, Integer> {

    @Autowired
    private EdicionesService edicionService;

    public EdicionesController(EdicionesService edicionService) {
        this.edicionService = edicionService;
    }

    @GetMapping("/api/ediciones")
    @Override
    public ResponseEntity<List<EdicionDTO>> get() {
        return edicionService.get();
    }

    @PostMapping("/api/ediciones")
    @Override
    public ResponseEntity<EdicionDTO> post(EdicionDTO edicionDTO) {
        return edicionService.post(edicionDTO);
    }

    @Transactional
    @PutMapping("/api/ediciones")
    @Override
    public ResponseEntity<EdicionDTO> put(EdicionDTO edicionDTO) {
        return edicionService.put(edicionDTO);
    }

    @DeleteMapping("/api/ediciones")
    @Override
    public ResponseEntity<EdicionDTO> delete(Integer id) {
        return edicionService.delete(id);
    }
}