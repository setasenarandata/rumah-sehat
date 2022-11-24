package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AdminModel;

public interface AdminService {
    void addAdmin(AdminModel admin);
    String encrypt(String pasword);

    AdminModel findByUsername(String admin);
}
