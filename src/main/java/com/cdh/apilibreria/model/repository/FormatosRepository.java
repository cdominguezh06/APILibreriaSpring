package com.cdh.apilibreria.model.repository;

import com.cdh.apilibreria.model.entities.Formato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatosRepository extends JpaRepository<Formato, Integer> {
}
