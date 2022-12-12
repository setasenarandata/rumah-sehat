package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.AppointmentModel;
import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {
    @Autowired
    TagihanDb tagihanDb;

    @Autowired
    AppointmentService appointmentService;

    @Override
    public List<TagihanModel> findAllByPasien(PasienModel pasien) {

        List<TagihanModel> userTagihanList = new ArrayList<TagihanModel>();
        List<TagihanModel> allTagihan = tagihanDb.findAll();
        for (TagihanModel tagihanModel : allTagihan) {
            AppointmentModel appointment = appointmentService.getAppointmentById(tagihanModel.getKode_appointment());
            if (appointment.getPasien() == pasien) userTagihanList.add(tagihanModel);
        }
        return userTagihanList;
    }

    @Override
    public TagihanModel getTagihanByKode(String kode){
        TagihanModel tagihan = tagihanDb.findTagihanModelByKode(kode);
        if (tagihan.getKode().equals(kode)) return tagihan;
        else return null;
    }

    @Override
    public TagihanModel updateStatusTagihan(String kode, TagihanModel tagihanUpdate){
        TagihanModel tagihan = getTagihanByKode(kode);
        tagihan.setIsPaid(tagihanUpdate.getIsPaid());
        tagihan.setJumlahTagihan(tagihanUpdate.getJumlahTagihan());
        tagihan.setTanggalBayar(LocalDateTime.now());
        tagihan.setTanggalTerbuat(tagihanUpdate.getTanggalTerbuat());
        return tagihanDb.save(tagihan);
    }


}
