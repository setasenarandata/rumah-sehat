package com.rumahsehat.rumahsehat.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;

    public void setToken(String token) {
        this.jwttoken = token;
    }

    public String getToken() {
        return this.jwttoken;
    }
}

