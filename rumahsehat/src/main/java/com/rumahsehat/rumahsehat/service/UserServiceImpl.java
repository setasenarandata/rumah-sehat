package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userdb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userdb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        Optional<UserModel> user = userdb.findUserByUsername(username);
        if (user.isPresent()){
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public List<UserModel> findAllUser(){
        return userdb.findAll();
    }
    @Override
    public String deleteUser (UserModel user){
        userdb.delete(user);
        return "berhasil";
    }

    @Override
    public boolean passwordEqual(String userPassword, String formPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(userPassword,formPassword);
    }

    @Override
    public void setPassword(UserModel user, String newPassword){
        user.setPassword(newPassword);
    }
}
