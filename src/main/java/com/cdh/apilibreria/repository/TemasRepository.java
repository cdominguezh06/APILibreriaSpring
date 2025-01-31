package com.cdh.apilibreria.repository;

import com.cdh.apilibreria.model.Temas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Integer> {
}
