package com.rumahsehat.rumahsehat.service;

import java.util.List;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.PasienModel;

public interface AppointmentRestService {
    List<AppointmentModel> listAppointmentPatient();
    void save(AppointmentModel appointmentModel);
    boolean isAppointmentValid(AppointmentModel appointmentModel);
    void refreshAppointment();
    List<AppointmentModel> listAppointmentThisPatient(PasienModel pasien);
}
