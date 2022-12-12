package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.config.JwtTokenUtil;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.service.PasienRestService;
import com.rumahsehat.rumahsehat.model.JwtResponse;
import com.rumahsehat.rumahsehat.model.PasienModel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class JWTRestController{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtils;

    @Autowired
    PasienRestService pasienRestService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserModel user) {
        try {
            log.info("Initiating user login");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    
            System.out.println("MASUK LOGIN JWT");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("INI USERNAME DIA: " + userDetails.getUsername());
            PasienModel pasien = pasienRestService.getPasienByUsername(userDetails.getUsername());
            // System.out.println("tes");
            // System.out.println(pasien);
            // if (pasienRestService.getPasienByUsername(userDetails.getUsername()) == null){
                
            // }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtils.generateJwtToken(authentication);
    
            JwtResponse authResponse = new JwtResponse(token, userDetails.getUsername());
            System.out.println("HOI" + ResponseEntity.ok(authResponse));
            return ResponseEntity.ok(authResponse);
        } catch (NullPointerException e){
            log.error("User unauthorized");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        } catch (Exception e) {
            log.error("Error has occured, failed to authenticate users");
            System.out.println(e);
            return ResponseEntity.ok("Failed to authenticate users");
        }
    }
}

