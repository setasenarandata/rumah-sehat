package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import com.rumahsehat.rumahsehat.model.DokterModel;

import java.util.List;

public interface ApotekerService {
    void addApoteker(ApotekerModel apoteker);
    String encrypt(String pasword);
    ApotekerModel findByUsername(String apoteker);

    List<ApotekerModel> findAllApoteker();
}
