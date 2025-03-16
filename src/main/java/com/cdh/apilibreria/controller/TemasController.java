package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.TemaDTO;
import com.cdh.apilibreria.services.TemasService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class TemasController implements GenericController<TemaDTO, Integer> {

    private final TemasService temasService;

    @Autowired
    public TemasController(TemasService temasService) {
        this.temasService = temasService;
    }

    @GetMapping("/api/temas")
    @Override
    public ResponseEntity<List<TemaDTO>> get() {
        return temasService.get();
    }

    @PostMapping("/api/temas")
    @Override
    public ResponseEntity<TemaDTO> post(TemaDTO temaDTO) {
        return temasService.post(temaDTO);
    }

    @Transactional
    @PutMapping("/api/temas")
    @Override
    public ResponseEntity<TemaDTO> put(TemaDTO temaDTO) {
        return temasService.put(temaDTO);
    }
    @DeleteMapping("/api/temas")
    @Override
    public ResponseEntity<TemaDTO> delete(Integer id) {
        return temasService.delete(id);
    }
}