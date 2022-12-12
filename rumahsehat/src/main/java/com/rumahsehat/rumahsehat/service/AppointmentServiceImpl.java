package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.AdminDb;
import com.rumahsehat.rumahsehat.repository.ApotekerDb;
import com.rumahsehat.rumahsehat.repository.AppointmentDb;
import com.rumahsehat.rumahsehat.repository.DokterDb;
import com.rumahsehat.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    DokterService dokterService;
    @Autowired
    TagihanDb tagihanDb;

    @Autowired
    AdminDb adminDb;

    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<AppointmentModel> getListAppointment(String username) {
        UserModel user = null;
        if (dokterService.findByUsername(username) != null) {
            user = dokterService.findByUsername(username);
        } else if (adminDb.findByUsername(username) != null) {
            user = adminDb.findByUsername(username);
        }
        List<AppointmentModel> find = appointmentDb.findAll();
        List<AppointmentModel> appointmentDokter = new ArrayList<AppointmentModel>();
        if (user == null) {
            return null;
        }
        if (user.getRole().equals("Dokter")) {
            for (AppointmentModel appointment : find) {
                if (appointment.getDokter().getId().equals(user.getId())) {
                    appointmentDokter.add(appointment);
                }
            }
            return appointmentDokter;
        } else if (user.getRole().equals("admin")) {
            return find;
        } else return null;
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

    public void appointTagihan(String kode) {
        AppointmentModel appointmentModel = getAppointmentById(kode);
        TagihanModel tagihanModel = new TagihanModel();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        String stringDate = date.format(dateFormat);

        LocalDateTime tanggalAppoint = LocalDateTime.parse(stringDate, dateFormat);
        tagihanModel.setTanggalTerbuat(tanggalAppoint);

        tagihanModel.setJumlahTagihan(appointmentModel.getDokter().getTarif());
        tagihanModel.setKode_appointment(appointmentModel.getKode());
        tagihanDb.save(tagihanModel);
    }

    @Override
    public String finishAppointment(String kode) {
        AppointmentModel appointment = getAppointmentById(kode);
        if (appointment.getResep() != null && (appointment.getResep().getIsDone() != true)) {
            return "Appointment Gagal Diubah";
        } else {
            appointment.setIsDone(true);
            appointTagihan(kode);
            return "Appointment Telah Diselesaikan";
        }
    }

}
