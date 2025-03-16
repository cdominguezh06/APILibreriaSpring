package com.cdh.apilibreria.model.repository;

import com.cdh.apilibreria.model.entities.Temas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Integer> {
}
