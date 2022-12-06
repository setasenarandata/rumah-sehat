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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtils.generateJwtToken(authentication);

        JwtResponse authResponse = new JwtResponse();
        authResponse.setToken(token);
        System.out.println("HOI" + ResponseEntity.ok(authResponse));
        return ResponseEntity.ok(authResponse);
    }
}

