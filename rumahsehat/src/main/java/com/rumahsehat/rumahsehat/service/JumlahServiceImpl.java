package com.rumahsehat.rumahsehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumahsehat.rumahsehat.model.JumlahModel;
import com.rumahsehat.rumahsehat.repository.JumlahDb;

@Service
@Transactional
public class JumlahServiceImpl implements JumlahService {
    @Autowired
    JumlahDb jumlahDb;
    
    @Override
    public List<JumlahModel> getAllJumlah() {
        return jumlahDb.findAll();
    }

}
