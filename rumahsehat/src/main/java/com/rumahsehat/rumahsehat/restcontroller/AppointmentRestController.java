package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.service.AppointmentRestService;
import com.rumahsehat.rumahsehat.service.DokterService;
import com.rumahsehat.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {

    Logger log = LoggerFactory.getLogger(AppointmentRestController.class);
    @Autowired
    private DokterService dokterService;

    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    private AppointmentRestService appointmentRestService;

    @GetMapping(value = "/list-doctor")
    public List<DokterModel> retrieveListDoctor(){
        return dokterService.findAllDokter();
    }

    @GetMapping(value = "/list-appointment")
    public List<AppointmentModel> getListAppointments() {
        appointmentRestService.refreshAppointment();
        return appointmentRestService.listAppointmentPatient();
    }

    @GetMapping(value = "/list-appointment/{username}")
    public List<AppointmentModel> getListAppointments(@PathVariable("username") String username) {
        appointmentRestService.refreshAppointment();
        PasienModel pasien = pasienRestService.getPasienByUsername(username);
        return appointmentRestService.listAppointmentThisPatient(pasien);
    }

    @GetMapping(value = "/appointment/{kode}")
    public AppointmentModel getOneAppointment(@PathVariable("kode") String kode) {
        return appointmentRestService.getOneAppointment(kode);
    }

    @PostMapping(value = "/appointment")
    public boolean addAppointment(@RequestBody Map<String, String> appointmentModel) throws Exception {
        try {
            log.info("Add an appointment");
            System.out.println("INSIDE APPOINTMENT POST MAPPING");
            System.out.println("Username: " + appointmentModel.get("username"));
            System.out.println("Doctor: " + appointmentModel.get("dokter"));
            System.out.println("Time: " + appointmentModel.get("time"));
            AppointmentModel appointment = new AppointmentModel();

            // set pasien
            appointment.setPasien(pasienRestService.getPasienByUsername(appointmentModel.get("username")));

            // set dokter
            appointment.setDokter(dokterService.findByUsername(appointmentModel.get("dokter")));

            // set default isDone to false
            appointment.setIsDone(false);

            // set waktu awal
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            appointment.setWaktuAwal(LocalDateTime.parse(appointmentModel.get("time"), formatter));

            // logic for nomor_urut
            String nomor_urut = String.valueOf(appointmentRestService.listAppointmentPatient().size());
            appointment.setKode("APT-" + nomor_urut);

            boolean isAppointmentValid = appointmentRestService.isAppointmentValid(appointment);

            if (isAppointmentValid) {
                appointmentRestService.save(appointment);
                return true;
            } else {
                System.out.println("False.");
                return false;
            }
        } catch (Exception e) {
            log.error("Appointment failed to create");
            System.out.println(e);
            return false;
        }
    }


}
