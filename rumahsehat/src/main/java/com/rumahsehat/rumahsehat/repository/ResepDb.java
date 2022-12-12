package com.rumahsehat.rumahsehat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import com.rumahsehat.rumahsehat.model.ResepModel;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, String> {
    Optional<ResepModel> findById(Long id);
}
