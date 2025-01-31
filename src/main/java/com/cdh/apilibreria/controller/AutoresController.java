package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Autor;
import com.cdh.apilibreria.repository.AutoresRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class AutoresController implements GenericController<Autor> {

    @Autowired
    private AutoresRepository autorRepository;

    @RequestMapping(path = "/api/autores", method = RequestMethod.GET)
    @Override
    public ResponseEntity<List<Autor>> get() {
        return ResponseEntity.ok(autorRepository.findAll());
    }

    @RequestMapping(path = "/api/autores", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Autor> post(@RequestBody Autor autor) {
        if (autorRepository.existsById(autor.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(autorRepository.save(autor));
    }

    @RequestMapping(path = "/api/autores", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<Autor> put(@RequestBody Autor autor) {
        if (autorRepository.existsById(autor.getId())) {
            delete(autor.getId());
            autorRepository.save(autor);
            return ResponseEntity.ok(autor);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/api/autores", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Autor> delete(int id) {
        if (autorRepository.existsById(id)) {
            Autor autor = autorRepository.getReferenceById(id);
            autorRepository.delete(autor);
            return ResponseEntity.ok(autor);
        }
        return ResponseEntity.notFound().build();
    }
}