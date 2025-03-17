package com.cdh.apilibreria.services;

import com.cdh.apilibreria.model.DTO.BuyOrderDTO;
import com.cdh.apilibreria.model.DTO.BuyOrderItemDTO;
import com.cdh.apilibreria.model.DTO.InformacionPagoDTO;
import com.cdh.apilibreria.model.entities.BuyOrder;
import com.cdh.apilibreria.model.entities.InformacionPago;
import com.cdh.apilibreria.model.entities.Libro;
import com.cdh.apilibreria.model.entities.User;
import com.cdh.apilibreria.model.mappers.BuyOrderDTOMapper;
import com.cdh.apilibreria.model.mappers.LibroDTOMapper;
import com.cdh.apilibreria.model.repository.BuyOrderRepository;
import com.cdh.apilibreria.model.repository.LibroRepository;
import com.cdh.apilibreria.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuyOrderService {

    private final BuyOrderDTOMapper mapper;
    private final BuyOrderRepository repository;
    private final UserRepository userRepository;
    private final LibroDTOMapper libroDTOMapper;
    private final LibroRepository libroRepository;

    @Autowired
    public BuyOrderService(@Qualifier("buyOrderDTOMapperImpl") BuyOrderDTOMapper mapper, BuyOrderRepository repository, UserRepository userRepository,
                           LibroDTOMapper libroDTOMapper, LibroRepository libroRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.userRepository = userRepository;
        this.libroDTOMapper = libroDTOMapper;
        this.libroRepository = libroRepository;
    }

    public ResponseEntity<List<BuyOrderDTO>> get() {
        return ResponseEntity.ok(repository.findAll().stream()
                .map(mapper::toBuyOrderDTO)
                .toList());
    }

    @Transactional
    public ResponseEntity<BuyOrderDTO> post(BuyOrderDTO buyOrderDTO) {
        Optional<User> userOptional = userRepository.findById(buyOrderDTO.user().username());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        User user = userOptional.get();

        BuyOrder buyOrder = new BuyOrder();
        buyOrder.setUser(user);

        for (BuyOrderItemDTO itemDTO : buyOrderDTO.buyOrderItems()) {
            Libro libro = libroRepository.findLibroByISBN(itemDTO.libro().ISBN());
            libro.setCantidad(libro.getCantidad()-itemDTO.cantidad());
            buyOrder.addItem(libro, itemDTO.cantidad());
        }

        return ResponseEntity.ok(mapper.toBuyOrderDTO(repository.save(buyOrder)));
    }

    @Transactional
    public ResponseEntity<BuyOrderDTO> put(BuyOrderDTO buyOrderDTO) {
        if (repository.existsById(buyOrderDTO.id())) {
            return ResponseEntity.ok(mapper.toBuyOrderDTO(repository.save(mapper.toBuyOrder(buyOrderDTO))));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<BuyOrderDTO> delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
