package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> findAllByPasien(String idPasien) {
        //condition buat nyari tagihan punya user itu doang
        return tagihanDb.findAll();
    }

}
