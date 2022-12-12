package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    TagihanModel findTagihanModelByKode(String kode);
}
