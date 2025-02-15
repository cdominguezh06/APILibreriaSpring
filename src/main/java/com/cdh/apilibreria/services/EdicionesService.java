package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.EdicionDTO;
import com.cdh.apilibreria.model.entities.Edicion;
import com.cdh.apilibreria.model.mappers.EdicionDTOMapper;
import com.cdh.apilibreria.repository.EdicionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicionesService {

    @Autowired
    private EdicionesRepository edicionRepository;

    @Autowired
    private EdicionDTOMapper edicionDTOMapper;

    public EdicionesService(EdicionDTOMapper edicionDTOMapper, EdicionesRepository edicionRepository) {
        this.edicionDTOMapper = edicionDTOMapper;
        this.edicionRepository = edicionRepository;
    }

    public ResponseEntity<List<EdicionDTO>> get() {
        return ResponseEntity.ok(edicionRepository.findAll().stream()
                .map(edicion -> edicionDTOMapper.toEdicionDTO(edicion))
                .toList());
    }

    public ResponseEntity<EdicionDTO> post(EdicionDTO edicion) {
        if (edicionRepository.existsById(edicion.id())) {
            return ResponseEntity.badRequest().build();
        }
        Edicion save = edicionRepository.save(edicionDTOMapper.toEdicion(edicion));
        return ResponseEntity.ok(edicionDTOMapper.toEdicionDTO(save));
    }

    public ResponseEntity<EdicionDTO> put(EdicionDTO edicion) {
        if (edicionRepository.existsById(edicion.id())) {
            delete(edicion.id());
            edicionRepository.save(edicionDTOMapper.toEdicion(edicion));
            return ResponseEntity.ok(edicion);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<EdicionDTO> delete(Integer id) {
        if (edicionRepository.existsById(id)) {
            Edicion edicion = edicionRepository.getReferenceById(id);
            edicionRepository.delete(edicion);
            return ResponseEntity.ok(edicionDTOMapper.toEdicionDTO(edicion));
        }
        return ResponseEntity.notFound().build();
    }
}
