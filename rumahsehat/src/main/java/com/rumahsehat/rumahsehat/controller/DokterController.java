package com.rumahsehat.rumahsehat.controller;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.model.JumlahModel;
import com.rumahsehat.rumahsehat.model.ObatModel;
import com.rumahsehat.rumahsehat.model.ResepModel;
import com.rumahsehat.rumahsehat.service.AppointmentService;
import com.rumahsehat.rumahsehat.service.DokterService;
import com.rumahsehat.rumahsehat.service.JumlahService;
import com.rumahsehat.rumahsehat.service.ObatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Autowired
    private ObatService obatService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private JumlahService jumlahService;

    @GetMapping("/user/add-dokter")
    public String addDokterFormPage(Model model){
        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);

        return "form-add-dokter";
    }

    @PostMapping("/user/add-dokter")
    public String addDokterSubmitPage(@ModelAttribute DokterModel dokter, Model model){
        dokter.setRole("Dokter");
        dokter.setIsSso(false);
        dokter.setPassword(dokterService.encrypt(dokter.getPassword()));
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);

        return "manajemen-user";
    }

    @GetMapping(value = "/user/viewall-dokter")
    private String listSemuaDokter(@ModelAttribute DokterModel dokter,Model model){
        List<DokterModel> listDokter = dokterService.findAllDokter();
        model.addAttribute("listDokter", listDokter);
        return "viewall-dokter";
    }

    @GetMapping(value = "/create-resep/{idAppointment}")
    private String createResep(@PathVariable("idAppointment") String idAppointment ,Model model) {
        ResepModel resep = new ResepModel();

        List<ObatModel> listObat = obatService.getListObat();
        
        
        List<JumlahModel> listJumlah = new ArrayList<>();
        List<JumlahModel> allJumlah = jumlahService.getAllJumlah();
        
        resep.setListJumlah(listJumlah);
        resep.getListJumlah().add(new JumlahModel());
        
        
        
        model.addAttribute("listObat",listObat);
        model.addAttribute("resep", resep);
        model.addAttribute("allJumlah", allJumlah);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("idAppointment", idAppointment);
        return "form-create-resep";
    }

    @PostMapping(value = "/create-resep/{idAppointment}")
    private String postCreateResep(@PathVariable("idAppointment") String idAppointment ,@ModelAttribute ResepModel resep, RedirectAttributes redirectAttributes, Model model) {
        try {
            resep.setCreatedAt(LocalDateTime.now());

            AppointmentModel appointment = appointmentService.getAppointmentById(idAppointment);
            resep.setAppointment(appointment);

            appointment.setResep(resep);

            resep.setIsDone(false);

            System.out.println("MASUK POST MAPPING CREATE RESEP");

            if (resep.getListJumlah() == null){
                resep.setListJumlah(new ArrayList<>());
            } else {
                List<JumlahModel> jumlah = resep.getListJumlah();
                List<JumlahModel> newJumlah = new ArrayList<JumlahModel>();

                for (int i = 0; i < jumlah.size(); i++) {
                    ObatModel obat = jumlah.get(i).getObat();
                    Integer quantity = jumlah.get(i).getKuantitas();

                    System.out.println(quantity);
                    System.out.println(obat);
                    ObatModel obatTerpilih = obatService.findById(obat.getId_obat());
                    
                    JumlahModel newModel = new JumlahModel();
                    newModel.setKuantitas(quantity);
                    newModel.setResep(resep);
                    newModel.setObat(obatTerpilih);
                    
                    newJumlah.add(newModel);

                }
                resep.setListJumlah(newJumlah);
            }

            for (JumlahModel element : resep.getListJumlah()) {
                System.out.println("Nama obat: " + element.getObat().getNama_obat());
            }

            dokterService.saveResep(resep);

            redirectAttributes.addFlashAttribute("Sukses", "Berhasil menambahkan resep");

            return "redirect:/viewall-resep";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }

    @PostMapping(value = "/create-resep/{idAppointment}", params = {"addRowObat"})
    private String addRowObatMultiple(@PathVariable String idAppointment,
                                    @ModelAttribute ResepModel resep,
                                    Model model
    ){
        List<ObatModel> listObat = obatService.getListObat();
        if(resep.getListJumlah() == null){
            resep.setListJumlah(new ArrayList<>());
        }
        resep.getListJumlah().add(new JumlahModel());
        List<JumlahModel> listAllJumlah = jumlahService.getAllJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listAllJumlah);
        model.addAttribute("listObat", listObat);

        return "form-create-resep";
    }

    @PostMapping(value = "/create-resep/{idAppointment}", params = {"deleteRowObat"})
    private String deleteRowObatMultiple(@PathVariable String idAppointment,
                                        @ModelAttribute ResepModel resep,
                                        @RequestParam(value = "deleteRowObat") Integer row,
                                        Model model) {
        
        List<ObatModel> listObat = obatService.getListObat();
        final Integer rowInt = Integer.valueOf(row);
        resep.getListJumlah().remove(rowInt.intValue());

        List<JumlahModel> listJumlah = resep.getListJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("listObat", listObat);

        return "form-create-resep";
    }

    @GetMapping("/detail-resep/{idResep}")
    public String viewDetailResep(@PathVariable Long idResep, Model model) {
        
        ResepModel resep = dokterService.getResepById(idResep);
        
        ApotekerModel apoteker = resep.getConfirmer();

        String namaDokter = resep.getAppointment().getDokter().getNama();
        String namaPasien = resep.getAppointment().getPasien().getNama();

        List<JumlahModel> listJumlahObat = resep.getListJumlah();

        String namaApoteker = "-";
        if (apoteker != null) {
            namaApoteker = apoteker.getNama();
        }

        model.addAttribute("resep", resep);
        model.addAttribute("namaDokter", namaDokter);
        model.addAttribute("namaPasien", namaPasien);
        model.addAttribute("listJumlahObat", listJumlahObat);
        model.addAttribute("namaApoteker", namaApoteker);


        return "view-detail-resep";
    }

    @GetMapping(value = "/viewall-resep")
    public String listResep(Model model){
        List<ResepModel> listResep = dokterService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }


}
