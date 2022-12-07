package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.PasienModel;

import java.util.List;
import java.util.Optional;

public interface PasienRestService {
    PasienModel addPasien(PasienModel pasien);

    List<PasienModel> getListPasien();

    Optional<PasienModel> getPasienById(String id);

    PasienModel getPasienByUsername(String username);

    String encrypt(String password);

    boolean topupSaldo(PasienModel pasien, int amount);
}
