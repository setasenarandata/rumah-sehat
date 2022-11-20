package com.rumahsehat.rumahsehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    // @Autowired
    // ServerProperties serverProperties;

    // private WebClient webClient = WebClient.builder().build();

    // @Autowired
    // private UserService userService;

    // @Autowired
    // private RoleService roleService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // @RequestMapping("/login")
    // public String login(Model model) {
    //     model.addAttribute("port", serverProperties.getPort());
    //     return "login";
    // }

    // @GetMapping("/validate-ticket")
    // public ModelAndView adminLoginSSO(
    //     @RequestParam(value = "ticket", required = false) String ticket,
    //     HttpServletRequest request
    // ) {
    //     ServiceResponse serviceResponse = this.webClient.get().uri(
    //         String.format(
    //             Setting.SERVER_VALIDATE_TICKET,
    //             ticket,
    //             Setting.CLIENT_LOGIN
    //         )
    //     ).retrieve().bodyToMono(ServiceResponse.class).block();

    //     Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
    //     String username = serviceResponse.getAuthenticationSuccess().getUser();

    //     UserModel user = userService.getUserByUsername(username);

    //     if (user == null) {
    //         user = new UserModel();
    //         user.setEmail(username + "@ui.ac.id");
    //         user.setNama(attributes.getNama());
    //         user.setPassword("belajarbelajar");
    //         user.setUsername(username);
    //         user.setIsSso(true);
    //         user.setRole(roleService.getById(Long.valueOf(1)));
    //         userService.addUser(user);
    //     }

    //     Authentication authentication = new UsernamePasswordAuthenticationToken(username, "belajarbelajar");

    //     SecurityContext securityContext = SecurityContextHolder.getContext();
    //     securityContext.setAuthentication(authentication);

    //     HttpSession httpSession = request.getSession(true);
    //     httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

    //     return new ModelAndView("redirect:/");
    // }

    // @GetMapping(value = "/login-sso")
    // public ModelAndView loginSSO() {
    //     return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    // }

    // @GetMapping(value = "/logout-sso")
    // public ModelAndView logoutSSO(Principal principal) {
    //     System.out.println("INSIDE LOGOUT SSO");
    //     UserModel user = userService.getUserByUsername(principal.getName());

    //     if (user.getIsSso() == false) {
    //         System.out.println("SSO IS FALSE");
    //         return new ModelAndView("redirect:/logout");
    //     }

    //     System.out.println("SSO IS TRUE");
    //     return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    // }

    // @GetMapping("/viewall-user")
    // public String viewAllUser(HttpServletRequest request ,Model model) {
    //     String username = request.getUserPrincipal().getName();
    //     UserModel user = userService.getUserByUsername(username);
    //     String role = user.getRole().getRole();
    //     System.out.println("Username: " + username + "; Role: " + role);

    //     if (role.equals("Admin")) {
    //         List<UserModel> listUser = userService.findAllUser();
    //         model.addAttribute("listUser", listUser);
    //     }
    //     return "viewall-user";
    // }

    // @GetMapping("/user/delete/{username}")
    // public String deleteUserFormPage(@PathVariable String username, Model model) {
    //     UserModel user = userService.getUserByUsername(username);
    //     if (user != null) {
    //         model.addAttribute("user", user);
    //         return "form-delete-user"; 
    //     } else {
    //         model.addAttribute("error", "Whoa! Who is this?");
    //         return "error";
    //     }
    // }

    // @PostMapping("/user/delete")
    // public String updateCourseSubmitPage(@ModelAttribute UserModel user, Model model) {
    //     UserModel selectedUser = userService.deleteUser(user);
    //     model.addAttribute("user", selectedUser.getNama());
    //     return "delete-user";
    // }

    // @GetMapping("/user/change/password")
    // public String changePasswordFormPage() {
    //     return "form-change-password";
    // }

    // @PostMapping("/user/change/password")
    // public String changePasswordSubmitPage(String username, String oldPassword, String newPassword, String newPassword2, Model model) {
    //     System.out.println("INSIDE CHANGE PASSWORD POST MAPPING");
    //     UserModel selectedUser = userService.getUserByUsername(username);
    //     BCryptPasswordEncoder bcyrpt = new BCryptPasswordEncoder();
    //     boolean isMatch = bcyrpt.matches(oldPassword, selectedUser.getPassword());
    //     boolean isValid = newPassword.equals(newPassword2);

    //     if (isMatch && isValid) {
    //         String hashedPassword = bcyrpt.encode(newPassword2);
    //         selectedUser.setPassword(hashedPassword);
    //         userService.changePassword(selectedUser);
    //         model.addAttribute("user", username);
    //         return "change-password";
    //     } else {
    //         model.addAttribute("error", "Failed to change password");
    //         return "error";
    //     }

    // }
}
