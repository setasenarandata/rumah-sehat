package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.DokterModel;

public interface DokterService {
    void addDokter(DokterModel dokter);
    DokterModel findByUsername(String username);
    String encrypt(String pasword);
}
