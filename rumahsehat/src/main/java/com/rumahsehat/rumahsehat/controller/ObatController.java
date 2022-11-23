package com.rumahsehat.rumahsehat.controller;


import com.rumahsehat.rumahsehat.model.ObatModel;
import com.rumahsehat.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping(value = "/obat/ubah/{id}")
    public String updateObatFormPage(@PathVariable String id, Model model){
        ObatModel obat =obatService.findById(id);
        model.addAttribute("obat", obat);

        return "form-ubah-stok-obat";
    }

    @PostMapping("/obat/ubah")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model, RedirectAttributes red){
        obatService.updateObat(obat);
        model.addAttribute("obat", obat);
        String pesan = "Stok obat dengan nama " + obat.getNama_obat() + " berhasil diubah";
        red.addFlashAttribute("pesan", pesan);
        return "redirect:/obat/ubah/" + obat.getId_obat();
    }
}
