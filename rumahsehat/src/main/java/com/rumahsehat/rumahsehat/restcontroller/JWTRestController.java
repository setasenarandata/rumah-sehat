package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.config.JwtTokenUtil;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class JWTRestController{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserModel user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    
            System.out.println("MASUK LOGIN JWT");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println("INI USERNAME DIA: " + userDetails.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtils.generateJwtToken(authentication);
    
            JwtResponse authResponse = new JwtResponse(token, userDetails.getUsername());
            System.out.println("HOI" + ResponseEntity.ok(authResponse));
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.ok("Failed to authenticate users");
        }
    }
}

