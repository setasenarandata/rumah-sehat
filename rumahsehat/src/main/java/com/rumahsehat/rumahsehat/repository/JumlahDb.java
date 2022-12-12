package com.rumahsehat.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rumahsehat.rumahsehat.model.JumlahModel;

public interface JumlahDb extends JpaRepository<JumlahModel, String>{
    
}
