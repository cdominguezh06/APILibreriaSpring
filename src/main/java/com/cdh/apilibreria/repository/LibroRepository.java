package com.cdh.apilibreria.repository;

import com.cdh.apilibreria.model.entities.Libro;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Libro findLibroByISBN(@NonNull String isbn);

    boolean existsLibroByISBN(@NonNull String isbn);
}
