package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import org.springframework.security.core.userdetails.User;

import java.util.List;
public interface AppointmentService {

    UserModel findUserByUsername(User user);

    List<AppointmentModel> getListAppointment(User used);

    AppointmentModel getAppointmentById(String kode);
    String finishAppointment(String kode);
}
