package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObatDb extends JpaRepository<ObatModel, String> {
    Optional<ObatModel> findById(String id_obat);
}
