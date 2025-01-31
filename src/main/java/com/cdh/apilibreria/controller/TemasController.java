package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Temas;
import com.cdh.apilibreria.repository.TemasRepository;
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
public class TemasController implements GenericController<Temas> {

    @Autowired
    private TemasRepository temaRepository;

    @RequestMapping(path = "/api/temas", method = RequestMethod.GET)
    @Override
    public ResponseEntity<List<Temas>> get() {
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @RequestMapping(path = "/api/temas", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Temas> post(@RequestBody Temas temas) {
        if (temaRepository.existsById(temas.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(temaRepository.save(temas));
    }

    @RequestMapping(path = "/api/temas", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<Temas> put(@RequestBody Temas temas) {
        if (temaRepository.existsById(temas.getId())) {
            delete(temas.getId());
            temaRepository.save(temas);
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/api/temas", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Temas> delete(int id) {
        if (temaRepository.existsById(id)) {
            Temas temas = temaRepository.getReferenceById(id);
            temaRepository.delete(temas);
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.notFound().build();
    }
}