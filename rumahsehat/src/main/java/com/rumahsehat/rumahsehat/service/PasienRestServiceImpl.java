package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{

    @Autowired
    private PasienDb pasienDb;

    @Override
    public Optional<PasienModel> getPasienById(String id) {
        Optional<PasienModel> pasien = pasienDb.findById(id);

        return pasien;
    }

    @Override
    public PasienModel addPasien(PasienModel pasien){
        pasien.setPassword(encrypt(pasien.getPassword()));
        return pasienDb.save(pasien);
    }
    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<PasienModel> getListPasien(){
        return pasienDb.findAll();
    }

}
