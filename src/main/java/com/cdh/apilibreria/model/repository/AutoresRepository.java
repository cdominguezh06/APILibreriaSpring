package com.cdh.apilibreria.model.repository;

import com.cdh.apilibreria.model.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Integer> {
}
