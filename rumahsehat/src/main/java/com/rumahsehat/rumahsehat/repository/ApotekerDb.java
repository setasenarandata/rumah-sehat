package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, String> {
    ApotekerModel findByUsername(String username);
}
