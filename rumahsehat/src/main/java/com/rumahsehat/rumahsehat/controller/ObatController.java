package com.rumahsehat.rumahsehat.controller;


import com.rumahsehat.rumahsehat.model.ObatModel;
import com.rumahsehat.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ObatController {
    @Autowired
    public ObatService obatService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/daftar-obat")
    public String listAllObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }
}
