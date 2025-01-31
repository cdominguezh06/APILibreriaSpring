package com.cdh.apilibreria.repository;

import com.cdh.apilibreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
