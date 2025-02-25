package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.InformacionPagoDTO;
import com.cdh.apilibreria.model.entities.InformacionPago;
import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.model.mappers.InformacionPagoDTOMapper;
import com.cdh.apilibreria.repository.InformacionPagoRepository;
import com.cdh.apilibreria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformacionPagoService {

    @Autowired
    private final InformacionPagoRepository informacionPagoRepository;

    @Autowired
    private final InformacionPagoDTOMapper informacionPagoDTOMapper;

    @Autowired
    private final UserRepository userRepository;

    public InformacionPagoService(InformacionPagoRepository informacionPagoRepository, InformacionPagoDTOMapper informacionPagoDTOMapper, UserRepository userRepository) {
        this.informacionPagoRepository = informacionPagoRepository;
        this.informacionPagoDTOMapper = informacionPagoDTOMapper;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<InformacionPagoDTO>> get() {
        return ResponseEntity.ok(informacionPagoRepository.findAll().stream()
                .map(informacionPagoDTOMapper::mapToDTO)
                .toList());
    }

    public ResponseEntity<InformacionPagoDTO> post(InformacionPagoDTO informacionPagoDTO) {
        InformacionPago entity = informacionPagoDTOMapper.mapToEntity(informacionPagoDTO);
        InformacionPago save = informacionPagoRepository.save(entity);
        return ResponseEntity.ok(informacionPagoDTOMapper.mapToDTO(save));
    }

    public ResponseEntity<InformacionPagoDTO> put(InformacionPagoDTO informacionPagoDTO) {
        if (informacionPagoRepository.existsById(informacionPagoDTO.numeroTarjeta())) {
            InformacionPago toPut = informacionPagoRepository.findById(informacionPagoDTO.numeroTarjeta()).get();
            informacionPagoRepository.save(toPut);
            return ResponseEntity.ok(informacionPagoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<InformacionPagoDTO> delete(String id) {
        try {
            if (informacionPagoRepository.existsById(id)) {
                InformacionPago informacionPago = informacionPagoRepository.findById(id).get();
                informacionPagoRepository.delete(informacionPago);
                return ResponseEntity.ok(informacionPagoDTOMapper.mapToDTO(informacionPago));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<InformacionPagoDTO> addInformacionPagoToUser(String userId, String numeroTarjeta) {
        if(!informacionPagoRepository.existsById(numeroTarjeta)) {
            return ResponseEntity.notFound().build();
        }
        if(!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        InformacionPago informacionPago = informacionPagoRepository.findById(numeroTarjeta).get();
        User user = userRepository.findById(userId).get();
        user.addInformacionPago(informacionPago);
        userRepository.save(user);
        return ResponseEntity.ok(informacionPagoDTOMapper.mapToDTO(informacionPago));
    }

    public ResponseEntity<List<InformacionPagoDTO>> getInformacionPagoByUser(String userId) {
        if(!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findById(userId).get();
        return ResponseEntity.ok(user.getInformacionesPago().stream()
                .map(informacionPagoDTOMapper::mapToDTO)
                .toList());
    }

}
