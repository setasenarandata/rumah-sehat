package com.rumahsehat.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumahsehat.rumahsehat.model.ResepModel;
import com.rumahsehat.rumahsehat.repository.ResepDb;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public void saveResep(ResepModel resep) {
        try {
            resepDb.save(resep);
            
        } catch (Exception e) {
            log.error("Error saving resep model");
            log.error("error", e);
        }
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }

    @Override
    public ResepModel getResepById(Long id) {
        return resepDb.findById(id).isPresent() ? resepDb.findById(id).get() : null;
    }
}
