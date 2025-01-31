package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Libro;
import com.cdh.apilibreria.repository.LibroRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class LibrosController implements GenericController<Libro> {

    @Autowired
    private LibroRepository libroRepository;

    @RequestMapping(path = "/api/libros", method = RequestMethod.GET)
    @ResponseBody
    @Override
    public ResponseEntity<List<Libro>> get() {
        return ResponseEntity.ok(libroRepository.findAll());

    }


    @RequestMapping(path = "/api/libros", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Libro> post(@RequestBody Libro libro) {
        if (libroRepository.existsById(libro.getId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(libroRepository.save(libro));
    }

    @RequestMapping(path = "/api/libros", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<Libro> put(Libro libro) {
        if (libroRepository.existsById(libro.getId())) {
            delete(libro.getId());
            libroRepository.save(libro);
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/api/libros", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Libro> delete(int id) {
        if (libroRepository.existsById(id)) {
            Libro libro = libroRepository.getReferenceById(id);
            libroRepository.delete(libro);
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build();
    }
}
