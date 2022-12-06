package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> findAllByPasien(String idPasien);
}
