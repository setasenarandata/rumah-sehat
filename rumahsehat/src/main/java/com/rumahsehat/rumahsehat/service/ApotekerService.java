package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.ApotekerModel;

public interface ApotekerService {
    void addApoteker(ApotekerModel apoteker);
    String encrypt(String pasword);
    ApotekerModel findByUsername(String apoteker);
}
