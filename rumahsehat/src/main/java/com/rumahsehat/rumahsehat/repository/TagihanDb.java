package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    TagihanModel findByKode(String kode);
}
