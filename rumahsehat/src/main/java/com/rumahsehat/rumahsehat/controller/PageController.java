package com.rumahsehat.rumahsehat.controller;

import com.rumahsehat.rumahsehat.Setting.Setting;
import com.rumahsehat.rumahsehat.model.AdminModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.AdminDb;
import com.rumahsehat.rumahsehat.repository.ApotekerDb;
import com.rumahsehat.rumahsehat.repository.DokterDb;
import com.rumahsehat.rumahsehat.repository.PasienDb;
import com.rumahsehat.rumahsehat.security.xml.Attributes;
import com.rumahsehat.rumahsehat.security.xml.ServiceResponse;
import com.rumahsehat.rumahsehat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class PageController {
    @Autowired
    AdminDb adminDb;


    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    AdminService adminService;

    @Autowired
    PasienDb pasienDb;

    @Autowired
    ServerProperties serverProperties;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/user/manajemen-user")
    public String manajemenUser(Model model) {
        return "manajemen-user";
    }

    private WebClient webClient = WebClient.builder().build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ) {
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        AdminModel user = adminDb.findByUsername(username);

        if (user == null) {
            user = new AdminModel();
            user.setEmail(username + "@ui.ac.id");
            user.setNama(attributes.getNama());
            user.setPassword("belajarbelajar");
            user.setUsername(username);
            user.setIsSso(true);
            user.setRole("admin");
            adminService.addAdmin(user);
        }
        System.out.println(user.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "belajarbelajar");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = apotekerDb.findByUsername(principal.getName());
        if(user == null) {
            user = adminDb.findByUsername(principal.getName());
        }
        if(user == null) {
            user = dokterDb.findByUsername(principal.getName());
        }
        if(user == null) {
            user = pasienDb.findByUsername(principal.getName());
        }
        if (user.getIsSso() == false){
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}
