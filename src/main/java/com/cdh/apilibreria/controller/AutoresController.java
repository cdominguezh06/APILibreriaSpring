package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.AutorDTO;
import com.cdh.apilibreria.services.AutoresService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class AutoresController implements GenericController<AutorDTO, Integer> {

    @Autowired
    private AutoresService autoresService;

    public AutoresController(AutoresService autoresService) {
        this.autoresService = autoresService;
    }

    @GetMapping("/api/autores")
    @Override
    public ResponseEntity<List<AutorDTO>> get() {
        return autoresService.get();
    }

    @PostMapping("/api/autores")
    @Override
    public ResponseEntity<AutorDTO> post(@RequestBody AutorDTO autor) {
        return autoresService.post(autor);
    }

    @Transactional
    @PutMapping("/api/autores")
    @Override
    public ResponseEntity<AutorDTO> put(@RequestBody AutorDTO autor) {
        return autoresService.put(autor);
    }

    @DeleteMapping("/api/autores")
    @Override
    public ResponseEntity<AutorDTO> delete(@RequestParam Integer id) {
        return autoresService.delete(id);
    }
}