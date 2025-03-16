package com.cdh.apilibreria.model.repository;

import com.cdh.apilibreria.model.entities.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(@NonNull String email);
}
