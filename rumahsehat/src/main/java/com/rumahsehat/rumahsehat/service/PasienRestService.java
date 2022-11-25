package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.PasienModel;

import java.util.List;

public interface PasienRestService {
    PasienModel addPasien(PasienModel pasien);

    List<PasienModel> getListPasien();

    String encrypt(String password);
}
