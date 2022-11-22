package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.ObatModel;
import com.rumahsehat.rumahsehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }
}
