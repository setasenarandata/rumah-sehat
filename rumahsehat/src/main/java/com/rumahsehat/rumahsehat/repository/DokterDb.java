package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, String> {
    DokterModel findByUsername(String username);
}
