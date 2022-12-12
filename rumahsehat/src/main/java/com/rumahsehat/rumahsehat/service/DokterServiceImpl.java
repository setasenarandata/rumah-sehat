package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.DokterModel;
import com.rumahsehat.rumahsehat.model.ResepModel;
import com.rumahsehat.rumahsehat.repository.DokterDb;
import com.rumahsehat.rumahsehat.repository.ResepDb;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DokterServiceImpl implements DokterService{

    @Autowired
    DokterDb dokterDb;

    @Autowired
    ResepDb resepDb;

    @Override
    public void addDokter(DokterModel dokter) {dokterDb.save(dokter);
    }

    @Override
    public DokterModel findByUsername(String username) {
        return dokterDb.findByUsername(username);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<DokterModel> findAllDokter(){
        return dokterDb.findAll();
    }
}

