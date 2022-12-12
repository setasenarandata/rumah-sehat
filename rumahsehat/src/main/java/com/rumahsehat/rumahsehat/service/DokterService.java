package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.model.ResepModel;

import java.util.List;

public interface DokterService {
    void addDokter(DokterModel dokter);
    DokterModel findByUsername(String username);

    List<DokterModel> findAllDokter();
    String encrypt(String pasword);

    boolean saveResep(ResepModel resep);
    List<ResepModel> getListResep();
    ResepModel getResepById(Long id);
}
