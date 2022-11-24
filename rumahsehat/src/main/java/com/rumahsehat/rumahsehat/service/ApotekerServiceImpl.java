package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.ApotekerModel;
import com.rumahsehat.rumahsehat.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public void addApoteker(ApotekerModel apoteker) {apotekerDb.save(apoteker);
    }

    @Override
    public ApotekerModel findByUsername(String apoteker) {
        return apotekerDb.findByUsername(apoteker);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
