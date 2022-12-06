package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    void addDokter(DokterModel dokter);
    DokterModel findByUsername(String username);

    List<DokterModel> findAllDokter();
    String encrypt(String pasword);
}
