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
    public boolean topupSaldo(PasienModel pasien, int amount) {
        int saldo = pasien.getSaldo();
        int newSaldo = saldo + amount;
        pasien.setSaldo(newSaldo);
        pasienDb.save(pasien);
        int updatedSaldo = pasien.getSaldo();
        return updatedSaldo == newSaldo ? true : false;
        
    }
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

    @Override
    public PasienModel getPasienByUsername(String username) {
        PasienModel pasien = pasienDb.findByUsername(username);
        if (pasien.getUsername().equals(username)){
            return pasien;
        } else {
            return null;
        }
    }
    
    @Override
    public PasienModel bayarTagihan(String username, int amount){
        PasienModel pasien = getPasienByUsername(username);
        pasien.setSaldo(pasien.getSaldo() - amount);
        return pasienDb.save(pasien);
    }

}
