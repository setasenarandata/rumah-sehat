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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

        List<AppointmentModel> listAppointment = appointmentService.getListAppointment(users);
        if (listAppointment != null) {
            model.addAttribute("listAppointment", listAppointment);
            model.addAttribute("role", role);
        }
        return "viewall-appointment";
    }
}
