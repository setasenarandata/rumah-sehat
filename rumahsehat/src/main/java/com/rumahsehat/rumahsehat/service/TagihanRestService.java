package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.TagihanModel;

public interface TagihanRestService {
    TagihanModel getTagihanByKode(String kode);
    TagihanModel updateStatusTagihan(String kode, TagihanModel tagihanUpdate);

}
