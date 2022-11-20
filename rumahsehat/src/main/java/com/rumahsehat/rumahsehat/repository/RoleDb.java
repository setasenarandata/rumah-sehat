package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDb extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRole(String role);
    Optional<RoleModel> findById(Long id);
}
