package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.LibroDTO;
import com.cdh.apilibreria.services.LibroService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class LibrosController implements GenericController<LibroDTO, String> {

    private final LibroService libroService;

    @Autowired
    public LibrosController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/api/libros")
    @Override
    public ResponseEntity<List<LibroDTO>> get() {
        return libroService.get();
    }

    @PostMapping("/api/libros")
    @Override
    public ResponseEntity<LibroDTO> post(@RequestBody LibroDTO libro) {
        return libroService.post(libro);
    }

    @Transactional
    @PutMapping("/api/libros")
    @Override
    public ResponseEntity<LibroDTO> put(@RequestBody LibroDTO libro) {
        return libroService.put(libro);
    }

    @DeleteMapping("/api/libros")
    @Override
    public ResponseEntity<LibroDTO> delete(@RequestParam String ISBN) {
       return libroService.delete(ISBN);
    }
}
