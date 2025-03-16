package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.LibroDTO;
import com.cdh.apilibreria.model.entities.Libro;
import com.cdh.apilibreria.model.mappers.LibroDTOMapper;
import com.cdh.apilibreria.model.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final LibroDTOMapper libroDTOMapper;

    @Autowired
    public LibroService(LibroRepository libroRepository, LibroDTOMapper libroDTOMapper) {
        this.libroRepository = libroRepository;
        this.libroDTOMapper = libroDTOMapper;
    }

    public ResponseEntity<List<LibroDTO>> get() {
        return ResponseEntity.ok(libroRepository.findAll().stream()
                .map(libroDTOMapper::toLibroDTO)
                .toList());

    }
    @Transactional
    public ResponseEntity<LibroDTO> post(LibroDTO libro) {
        Libro save = libroRepository.save(libroDTOMapper.toLibro(libro));
        return ResponseEntity.ok(libroDTOMapper.toLibroDTO(save));
    }

    @Transactional
    public ResponseEntity<LibroDTO> put(LibroDTO libro) {
        if (libroRepository.existsLibroByISBN(libro.ISBN())) {
            Libro libro1 = libroDTOMapper.toLibro(libro);
            libro1.setId(libroRepository.findLibroByISBN(libro.ISBN()).getId());
            libroRepository.save(libro1);
            return ResponseEntity.ok(libro);
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
