package com.cdh.apilibreria.repository;

import com.cdh.apilibreria.model.entities.Edicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdicionesRepository extends JpaRepository<Edicion, Integer> {
}
