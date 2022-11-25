package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.AdminDb;
import com.rumahsehat.rumahsehat.repository.ApotekerDb;
import com.rumahsehat.rumahsehat.repository.AppointmentDb;
import com.rumahsehat.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    UserService userService;
    @Autowired
    AppointmentDb appointmentDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    AdminDb adminDb;

    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<AppointmentModel> getListAppointment(User used) {
        UserModel user = findUserByUsername(used);
        List<AppointmentModel> find = appointmentDb.findAll();
        if (user == null) {
            return null;
        }
        if (user.getRole().equals("dokter")) {
            List<AppointmentModel> appointmentDokter = new ArrayList<AppointmentModel>();
            for (AppointmentModel appointment : find) {
                if (appointment.getDokter().getId().equals(user.getId())) {
                    appointmentDokter.add(appointment);
                }
            }
            find = appointmentDokter;
        } else if (user.getRole().equals("pasien")) {
            List<AppointmentModel> appointmentPasien = new ArrayList<AppointmentModel>();
            for (AppointmentModel appointment : find) {
                if (appointment.getDokter().getId().equals(user.getId())) {
                    appointmentPasien.add(appointment);
                }
            }
            find = appointmentPasien;
        }
        return find;
    }

    @Override
    public AppointmentModel getAppointmentById(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findById(kode);
        if (appointment.isPresent()) {
            return appointment.get();
        } else return null;
    }

    @Override
    public UserModel findUserByUsername(User user) {
        String name = user.getUsername();
        String role = userService.getUserRole();
        if (role.equals("admin")) {
            return adminDb.findByUsername(name);
        } else if (role.equals("dokter")) {
            return adminDb.findByUsername(name);
        } return null;
    }

}
