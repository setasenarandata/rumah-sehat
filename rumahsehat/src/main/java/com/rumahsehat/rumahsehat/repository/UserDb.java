package com.rumahsehat.rumahsehat.repository;


import com.rumahsehat.rumahsehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDb extends JpaRepository<UserModel, Long> {
}
