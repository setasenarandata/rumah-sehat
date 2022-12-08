package com.rumahsehat.rumahsehat.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.repository.AppointmentDb;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> listAppointmentPatient(){
        return appointmentDb.findAll();
    }

    @Override
    public void save(AppointmentModel appointmentModel){
        appointmentDb.save(appointmentModel);
    }


    @Override
    public boolean isAppointmentValid(AppointmentModel appointmentModel){
        for (AppointmentModel appointment : appointmentDb.findAll()){
            // only iterates over false isDone appointments
            if (!appointment.getIsDone()) {
                // only iterates over the selected doctor
                if (appointment.getDokter().getNama().equals(appointmentModel.getDokter().getNama())) {
                    // logic comparison
                    Long diff = Duration.between(appointment.getWaktuAwal(), appointmentModel.getWaktuAwal()).toMillis();
                    diff = TimeUnit.MILLISECONDS.toSeconds(diff);
                    if (diff >= 0 && diff < 3600){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void refreshAppointment() {
        LocalDateTime today = LocalDateTime.now();
        for (AppointmentModel appointment: appointmentDb.findAll()) {
            // logic comparison
            Long diff = Duration.between(appointment.getWaktuAwal(), today).toMillis();
            diff = TimeUnit.MILLISECONDS.toSeconds(diff);
            if (diff >= 3600){
                appointment.setIsDone(true);
            }
        }
    }

    @Override
    public List<AppointmentModel> listAppointmentThisPatient(PasienModel pasien) {
        List<AppointmentModel> listAppointment = appointmentDb.findAll();
        List<AppointmentModel> appointmentPasien = new ArrayList<AppointmentModel>();
        for (AppointmentModel appointmentModel : listAppointment) {
            if (appointmentModel.getPasien() == pasien) {
                appointmentPasien.add(appointmentModel);
            }
        }

        return appointmentPasien;
    }

    @Override
    public AppointmentModel getOneAppointment(String kode) {
        return appointmentDb.findById(kode).get();
    }
}
