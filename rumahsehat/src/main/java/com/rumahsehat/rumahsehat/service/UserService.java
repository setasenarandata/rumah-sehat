package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getUserByUsername(String username);
    List<UserModel> findAllUser();
    String deleteUser (UserModel user);
    boolean passwordEqual(String userPassword, String formPassword);
    void setPassword(UserModel user, String newPassword);
}
