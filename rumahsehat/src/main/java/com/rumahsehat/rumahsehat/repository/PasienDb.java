package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
    PasienModel findByUsername(String username);
}
