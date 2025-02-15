package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.TemaDTO;
import com.cdh.apilibreria.model.entities.Temas;
import com.cdh.apilibreria.model.mappers.TemaDTOMapper;
import com.cdh.apilibreria.repository.TemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemasService {

    @Autowired
    private TemasRepository temaRepository;

    @Autowired
    private TemaDTOMapper temaDTOMapper;

    public TemasService(TemasRepository temaRepository, TemaDTOMapper temaDTOMapper) {
        this.temaRepository = temaRepository;
        this.temaDTOMapper = temaDTOMapper;
    }

    public ResponseEntity<List<TemaDTO>> get() {
        return ResponseEntity.ok(temaRepository.findAll().stream()
                .map(temas -> temaDTOMapper.toTemaDTO(temas)
                ).toList());
    }

    public ResponseEntity<TemaDTO> post(TemaDTO temas) {
        if (temaRepository.existsById(temas.id())) {
            return ResponseEntity.badRequest().build();
        }
        Temas save = temaRepository.save(temaDTOMapper.toTema(temas));
        return ResponseEntity.ok(temaDTOMapper.toTemaDTO(save));
    }

    public ResponseEntity<TemaDTO> put(TemaDTO temas) {
        if (temaRepository.existsById(temas.id())) {
            delete(temas.id());
            temaRepository.save(temaDTOMapper.toTema(temas));
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<TemaDTO> delete(Integer id) {
        if (temaRepository.existsById(id)) {
            Temas temas = temaRepository.getReferenceById(id);
            temaRepository.delete(temas);
            return ResponseEntity.ok(temaDTOMapper.toTemaDTO(temas));
        }
        return ResponseEntity.notFound().build();
    }
}
