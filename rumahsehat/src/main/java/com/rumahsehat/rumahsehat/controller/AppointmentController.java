package com.rumahsehat.rumahsehat.controller;


import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.service.AppointmentService;
import com.rumahsehat.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class AppointmentController {
@Autowired
AppointmentService appointmentService;
@Autowired
UserService userService;

    @GetMapping("/appointment/viewall")
    public String listAppointment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User users = (User) authentication.getPrincipal();
        String role = userService.getUserRole();
        System.out.println("masuk");
        System.out.println(users.getUsername());
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment(users.getUsername());
        System.out.println(listAppointment.size());
        if (listAppointment != null) {
            model.addAttribute("listAppointment", listAppointment);
            model.addAttribute("role", role);
        }
        return "viewall-appointment";
    }

    @GetMapping("/appointment/view")
    public String detailAppointment(@RequestParam(value = "kode") String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentById(kode);
        String role = userService.getUserRole();
        model.addAttribute("appointment", appointment);
        model.addAttribute("role", role);
        return "detail-appointment";
    }

    @GetMapping("/appointment/finish")
    public String finishAppointment(@RequestParam(value = "kode") String kode, RedirectAttributes redirectAttributes) {
        String finish = appointmentService.finishAppointment(kode);
        redirectAttributes.addFlashAttribute("finish", finish);
        return "redirect:/appointment/view";
    }
}
