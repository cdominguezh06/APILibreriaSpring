package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.DTO.FormatoDTO;
import com.cdh.apilibreria.model.entities.Formato;
import com.cdh.apilibreria.repository.FormatosRepository;
import com.cdh.apilibreria.services.FormatosService;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class FormatosController implements GenericController<FormatoDTO, Integer> {

    @Autowired
    private FormatosService formatosService;

    public FormatosController(FormatosService formatosService) {
        this.formatosService = formatosService;
    }

    @GetMapping("/api/formatos")
    @Override
    public ResponseEntity<List<FormatoDTO>> get() {
        return formatosService.get();
    }

    @PostMapping("/api/formatos")
    @Override
    public ResponseEntity<FormatoDTO> post(FormatoDTO formatoDTO) {
        return formatosService.post(formatoDTO);
    }

    @Transactional
    @PutMapping("/api/formatos")
    @Override
    public ResponseEntity<FormatoDTO> put(FormatoDTO formatoDTO) {
        return formatosService.put(formatoDTO);
    }

    @DeleteMapping("/api/formatos")
    @Override
    public ResponseEntity<FormatoDTO> delete(Integer id) {
        return formatosService.delete(id);
    }
}