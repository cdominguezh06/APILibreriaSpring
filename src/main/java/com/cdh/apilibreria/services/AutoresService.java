package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.AutorDTO;
import com.cdh.apilibreria.model.entities.Autor;
import com.cdh.apilibreria.model.mappers.AutorDTOMapper;
import com.cdh.apilibreria.model.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresService {

    private final AutoresRepository autorRepository;
    private final AutorDTOMapper autorDTOMapper;
    @Autowired
    public AutoresService(AutoresRepository autoresRepository, AutorDTOMapper autorDTOMapper) {
        this.autorRepository = autoresRepository;
        this.autorDTOMapper = autorDTOMapper;
    }

    public ResponseEntity<List<AutorDTO>> get() {
        return ResponseEntity.ok(autorRepository.findAll().stream()
                .map(autorDTOMapper::toAutorDTO)
                .toList());
    }

    public ResponseEntity<AutorDTO> post(AutorDTO autor) {
        Autor save = autorRepository.save(autorDTOMapper.toAutor(autor));
        return ResponseEntity.ok(autorDTOMapper.toAutorDTO(save));
    }

    public ResponseEntity<AutorDTO> put(AutorDTO autor) {
        if (autorRepository.existsById(autor.id())) {
            autorRepository.save(autorDTOMapper.toAutor(autor));
            return ResponseEntity.ok(autor);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<AutorDTO> delete(Integer id) {
        if (autorRepository.existsById(id)) {
            Autor autor = autorRepository.getReferenceById(id);
            autorRepository.delete(autor);
            return ResponseEntity.ok(autorDTOMapper.toAutorDTO(autor));
        }
        return ResponseEntity.notFound().build();
    }

}
