package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.ObatModel;
import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();
    ObatModel findById(String id);
    void updateObat(ObatModel obat);
}
