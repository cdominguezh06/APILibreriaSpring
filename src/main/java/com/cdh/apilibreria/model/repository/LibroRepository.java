package com.cdh.apilibreria.model.repository;

import com.cdh.apilibreria.model.entities.*;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Libro findLibroByISBN(@NonNull String isbn);
    boolean existsLibroByISBN(@NonNull String isbn);
}
