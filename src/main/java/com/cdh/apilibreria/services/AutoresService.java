package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.AutorDTO;
import com.cdh.apilibreria.model.entities.Autor;
import com.cdh.apilibreria.model.mappers.AutorDTOMapper;
import com.cdh.apilibreria.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autorRepository;

    @Autowired
    private AutorDTOMapper autorDTOMapper;

    public AutoresService(AutoresRepository autoresRepository, AutorDTOMapper autorDTOMapper) {
        this.autorRepository = autoresRepository;
        this.autorDTOMapper = autorDTOMapper;
    }

    public ResponseEntity<List<AutorDTO>> get() {
        return ResponseEntity.ok(autorRepository.findAll().stream()
                .map(a -> autorDTOMapper.toAutorDTO(a))
                .toList());
    }

    public ResponseEntity<AutorDTO> post(@RequestBody AutorDTO autor) {
        if (!autorRepository.existsById(autor.id())) {
            return ResponseEntity.badRequest().build();
        }
        Autor save = autorRepository.save(autorDTOMapper.toAutor(autor));
        return ResponseEntity.ok(autorDTOMapper.toAutorDTO(save));
    }

    public ResponseEntity<AutorDTO> put(@RequestBody AutorDTO autor) {
        if (autorRepository.existsById(autor.id())) {
            delete(autor.id());
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
