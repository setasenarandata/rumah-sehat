package com.rumahsehat.rumahsehat.controller;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.service.ApotekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ApotekerController {
    @Qualifier("apotekerServiceImpl")
    @Autowired
    private ApotekerService apotekerService;

    @GetMapping("/user/add-apoteker")
    public String addApotekerFormPage(Model model){
        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);


        return "form-add-apoteker";
    }

    @PostMapping("/user/add-apoteker")
    public String addApotekerSubmitPage(@ModelAttribute ApotekerModel apoteker, Model model){
        apoteker.setRole("Apoteker");
        apoteker.setIsSso(false);
        apoteker.setPassword(apotekerService.encrypt(apoteker.getPassword()));

        apotekerService.addApoteker(apoteker);
        model.addAttribute("apoteker", apoteker);

        return "manajemen-user";
    }
    @GetMapping(value = "/user/viewall-apoteker")
    private String listSemuaApoteker(@ModelAttribute ApotekerModel apoteker, Model model){
        List<ApotekerModel> listApoteker = apotekerService.findAllApoteker();
        model.addAttribute("listApoteker", listApoteker);
        return "viewall-apoteker";
    }

}
