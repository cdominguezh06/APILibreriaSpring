package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Edicion;
import com.cdh.apilibreria.repository.EdicionesRepository;
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
public class EdicionesController implements GenericController<Edicion> {

    @Autowired
    private EdicionesRepository edicionRepository;

    @RequestMapping(path = "/api/ediciones", method = RequestMethod.GET)
    @Override
    public ResponseEntity<List<Edicion>> get() {
        return ResponseEntity.ok(edicionRepository.findAll());
    }

    @RequestMapping(path = "/api/ediciones", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Edicion> post(@RequestBody Edicion edicion) {
        if (edicionRepository.existsById(edicion.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(edicionRepository.save(edicion));
    }

    @RequestMapping(path = "/api/ediciones", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<Edicion> put(@RequestBody Edicion edicion) {
        if (edicionRepository.existsById(edicion.getId())) {
            delete(edicion.getId());
            edicionRepository.save(edicion);
            return ResponseEntity.ok(edicion);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/api/ediciones", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Edicion> delete(int id) {
        if (edicionRepository.existsById(id)) {
            Edicion edicion = edicionRepository.getReferenceById(id);
            edicionRepository.delete(edicion);
            return ResponseEntity.ok(edicion);
        }
        return ResponseEntity.notFound().build();
    }
}