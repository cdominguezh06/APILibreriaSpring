package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.FormatoDTO;
import com.cdh.apilibreria.model.entities.Formato;
import com.cdh.apilibreria.model.mappers.FormatoDTOMapper;
import com.cdh.apilibreria.repository.FormatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FormatosService {

    @Autowired
    private FormatosRepository formatosRepository;

    @Autowired
    private FormatoDTOMapper formatoDTOMapper;

    public FormatosService(FormatosRepository formatosRepository, FormatoDTOMapper formatoDTOMapper) {
        this.formatosRepository = formatosRepository;
        this.formatoDTOMapper = formatoDTOMapper;
    }

    public ResponseEntity<List<FormatoDTO>> get() {
        return ResponseEntity.ok(formatosRepository.findAll().stream()
                .map(f -> formatoDTOMapper.toFormatoDTO(f))
                .toList());
    }

    public ResponseEntity<FormatoDTO> post(@RequestBody FormatoDTO formato) {
        Formato save = formatosRepository.save(formatoDTOMapper.toFormato(formato));
        return ResponseEntity.ok(formatoDTOMapper.toFormatoDTO(save));
    }

    public ResponseEntity<FormatoDTO> put(@RequestBody FormatoDTO formato) {
        if (formatosRepository.existsById(formato.id())) {
            delete(formato.id());
            formatosRepository.save(formatoDTOMapper.toFormato(formato));
            return ResponseEntity.ok(formato);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<FormatoDTO> delete(Integer id) {
        if (formatosRepository.existsById(id)) {
            Formato formato = formatosRepository.getReferenceById(id);
            formatosRepository.delete(formato);
            return ResponseEntity.ok(formatoDTOMapper.toFormatoDTO(formato));
        }
        return ResponseEntity.notFound().build();
    }
}
