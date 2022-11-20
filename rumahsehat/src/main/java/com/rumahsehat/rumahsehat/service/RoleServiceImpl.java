package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.RoleModel;
import com.rumahsehat.rumahsehat.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAllRole(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel getById(Long id) {
        Optional<RoleModel> role = roleDb.findById(id);
        if (role.isPresent()){
            return role.get();
        } else {
            return null;
        }
    }
}
