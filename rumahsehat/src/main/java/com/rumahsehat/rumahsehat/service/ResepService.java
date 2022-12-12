package com.rumahsehat.rumahsehat.service;

import java.util.List;

import com.rumahsehat.rumahsehat.model.ResepModel;

public interface ResepService {
    void saveResep(ResepModel resep);

    List<ResepModel> getListResep();

    ResepModel getResepById(Long id);
}
