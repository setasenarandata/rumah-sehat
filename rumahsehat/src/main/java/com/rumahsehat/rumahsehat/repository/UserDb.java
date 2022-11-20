package com.rumahsehat.rumahsehat.repository;


import com.rumahsehat.rumahsehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    Optional<UserModel> findUserByUsername(String username);
}
