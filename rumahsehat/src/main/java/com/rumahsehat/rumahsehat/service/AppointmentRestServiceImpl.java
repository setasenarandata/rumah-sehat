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
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.repository.AppointmentDb;
import com.rumahsehat.rumahsehat.repository.TagihanDb;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {
    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    TagihanDb tagihanDb;

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
        try {
            LocalDateTime today = LocalDateTime.now();
            System.out.println("INSIDE REFRESH APPOINTMENT");
            for (AppointmentModel appointment: appointmentDb.findAll()) {
                // logic comparison
                Long diff = Duration.between(appointment.getWaktuAwal(), today).toMillis();
                diff = TimeUnit.MILLISECONDS.toSeconds(diff);
                if (diff >= 3600){
                    System.out.println("CEK TAGIHAN");
                    System.out.println(appointment.getTagihan());
                    if (appointment.getTagihan() == null) {
                        Integer currBill = tagihanDb.findAll().size()+1; 
                        TagihanModel tagihan = new TagihanModel();
                        tagihan.setKode("BILL-" + String.valueOf(currBill));
                        tagihan.setKode_appointment(appointment.getKode());
                        tagihan.setIsPaid(false);
                        tagihan.setJumlahTagihan(appointment.getDokter().getTarif());
                        tagihan.setTanggalTerbuat(LocalDateTime.now());

                        System.out.println("TAGIHAN ENTRIES");
                        System.out.println(tagihan.getKode());
                        System.out.println(tagihan.getKode_appointment());
                        System.out.println(tagihan.getIsPaid());
                        System.out.println(tagihan.getJumlahTagihan());
                        System.out.println(tagihan.getTanggalTerbuat());
                        System.out.println("STAGE 0");
                        tagihanDb.save(tagihan);
                        System.out.println("STAGE 1");
                        
                    }
                    
                    appointment.setIsDone(true);

                }
            }
        } catch (Exception e) {
            System.out.println("FOUND AN ERROR");
            System.out.println(e);
        }
    }

    @Override
    public List<AppointmentModel> listAppointmentThisPatient(PasienModel pasien) {
        System.out.println("INSIDE LIST APPOINTMENT THIS PATIENT");
        List<AppointmentModel> listAppointment = appointmentDb.findAll();
        System.out.println("Size: " + listAppointment.size());
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
