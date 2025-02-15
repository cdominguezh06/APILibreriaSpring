package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.TemaDTO;
import com.cdh.apilibreria.model.entities.Temas;
import com.cdh.apilibreria.repository.TemasRepository;
import com.cdh.apilibreria.services.TemasService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class TemasController implements GenericController<TemaDTO, Integer> {

    @Autowired
    private TemasService temasService;

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