package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.LibroDTO;
import com.cdh.apilibreria.model.entities.Libro;
import com.cdh.apilibreria.model.mappers.LibroDTOMapper;
import com.cdh.apilibreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroDTOMapper libroDTOMapper;

    public LibroService(LibroRepository libroRepository, LibroDTOMapper libroDTOMapper) {
        this.libroRepository = libroRepository;
        this.libroDTOMapper = libroDTOMapper;
    }

    public ResponseEntity<List<LibroDTO>> get() {
        return ResponseEntity.ok(libroRepository.findAll().stream()
                .map(l -> libroDTOMapper.toLibroDTO(l))
                .toList());

    }
    @Transactional
    public ResponseEntity<LibroDTO> post(@RequestBody LibroDTO libro) {
        Libro save = libroRepository.save(libroDTOMapper.toLibro(libro));
        return ResponseEntity.ok(libroDTOMapper.toLibroDTO(save));
    }

    @Transactional
    public ResponseEntity<LibroDTO> put(@RequestBody LibroDTO libro) {
        if (libroRepository.existsLibroByISBN(libro.ISBN())) {
            Libro toPut = libroRepository.findLibroByISBN(libro.ISBN());
            libroRepository.save(toPut);
            return ResponseEntity.ok(libroDTOMapper.toLibroDTO(toPut));
        }
        System.out.println(libro);
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<LibroDTO> delete(@RequestParam String ISBN) {
        try {
            if (libroRepository.existsLibroByISBN(ISBN)) {
                Libro libro = libroRepository.findLibroByISBN(ISBN);
                libroRepository.delete(libro);
                return ResponseEntity.ok(libroDTOMapper.toLibroDTO(libro));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().eTag(e.getMessage()).build();
        }
    }
}
