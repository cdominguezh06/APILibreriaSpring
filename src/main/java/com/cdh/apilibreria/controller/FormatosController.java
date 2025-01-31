package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Formato;
import com.cdh.apilibreria.repository.FormatosRepository;
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
public class FormatosController implements GenericController<Formato> {

    @Autowired
    private FormatosRepository formatoRepository;

    @RequestMapping(path = "/api/formatos", method = RequestMethod.GET)
    @Override
    public ResponseEntity<List<Formato>> get() {
        return ResponseEntity.ok(formatoRepository.findAll());
    }

    @RequestMapping(path = "/api/formatos", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Formato> post(@RequestBody Formato formato) {
        if (formatoRepository.existsById(formato.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(formatoRepository.save(formato));
    }

    @RequestMapping(path = "/api/formatos", method = RequestMethod.PUT)
    @Override
    public ResponseEntity<Formato> put(@RequestBody Formato formato) {
        if (formatoRepository.existsById(formato.getId())) {
            delete(formato.getId());
            formatoRepository.save(formato);
            return ResponseEntity.ok(formato);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/api/formatos", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Formato> delete(int id) {
        if (formatoRepository.existsById(id)) {
            Formato formato = formatoRepository.getReferenceById(id);
            formatoRepository.delete(formato);
            return ResponseEntity.ok(formato);
        }
        return ResponseEntity.notFound().build();
    }
}