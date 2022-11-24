package com.rumahsehat.rumahsehat.controller;

import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

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

        return "redirect:/";
    }
}
