package com.rumahsehat.rumahsehat.repository;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {

}


