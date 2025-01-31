package com.cdh.apilibreria.repository;

import com.cdh.apilibreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends JpaRepository<Autor, Integer> {
}
