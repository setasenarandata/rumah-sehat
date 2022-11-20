package com.rumahsehat.rumahsehat.controller;

import com.rumahsehat.rumahsehat.model.RoleModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.service.RoleService;
import com.rumahsehat.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAllRole();
        model.addAttribute("user",user);
        model.addAttribute("listRole",listRole);
        return "form-add-user";

    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        user.setIsSso(false);
        userService.addUser(user);
        model.addAttribute("user",user);
        return "redirect:/";
    }

    @GetMapping(value = "/viewall")
    private String listSemuaUser(@ModelAttribute UserModel user,Model model){
//        if (user.getRole().equals("Admin")){
        List<UserModel> listUser = userService.findAllUser();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
//        } else {
//            return "gagal-buka-halaman";
//        }
    }

}
