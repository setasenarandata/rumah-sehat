package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> findAllByPasien(PasienModel pasien);
    TagihanModel getTagihanByKode(String kode);
    TagihanModel updateStatusTagihan(String kode, TagihanModel tagihanUpdate);
}
