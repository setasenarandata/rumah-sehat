package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AdminModel;
import com.rumahsehat.rumahsehat.repository.AdminDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDb adminDb;

    @Override
    public void addAdmin(AdminModel admin) {
        String pass = encrypt(admin.getPassword());
        admin.setPassword(pass);
        adminDb.save(admin);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public AdminModel findByUsername(String admin) {
        return adminDb.findByUsername(admin);
    }
}
