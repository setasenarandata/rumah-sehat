package com.rumahsehat.rumahsehat.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
    private String jwttoken;
    private String username;

    public JwtResponse(String token, String name) {
        this.jwttoken = token;
        this.username = name;
    }
}

