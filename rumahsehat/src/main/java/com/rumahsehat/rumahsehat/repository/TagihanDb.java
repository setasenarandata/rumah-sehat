package com.rumahsehat.rumahsehat.repository;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
}
