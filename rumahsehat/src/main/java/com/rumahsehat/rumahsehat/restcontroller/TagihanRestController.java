package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import lombok.extern.slf4j.Slf4j;

import com.rumahsehat.rumahsehat.service.PasienRestService;
import com.rumahsehat.rumahsehat.service.TagihanRestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class TagihanRestController {
    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    private PasienRestService pasienRestService;

    @GetMapping(value = "/tagihan/{kode}")
    private TagihanModel getTagihanByIdPasienAndKodeTagihan(
            @PathVariable("kode") String kode
    ) {
        log.info("Initiating getTagihanByKode with kode: " + kode);
        try{
            return tagihanRestService.getTagihanByKode(kode);
        }
        catch (NoSuchElementException e){
            log.error("Error has occured. Cannot find tagihan.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }

    @PutMapping(value="/tagihan/{kode}")
    private TagihanModel updateStatusTagihan(@PathVariable("kode") String kode, @RequestBody TagihanModel tagihanUpdate){
        try{
            log.info("Initiating update status tagihan with kode: " + kode);
            return tagihanRestService.updateStatusTagihan(kode, tagihanUpdate);
        }
        catch (NoSuchElementException e){
            log.error("Error has occured. Cannot find tagihan.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }

    @GetMapping(value = "/list-tagihan/{pasienId}")
    private List<TagihanModel> getTagihanByPasienId(@PathVariable("pasienId") String pasienId) {
        System.out.println("INSIDE LIST TAGIHAN PASIEN");
        PasienModel pasien = pasienRestService.getPasienByUsername(pasienId);
        System.out.println(pasien.getNama());
        return tagihanRestService.findAllByPasien(pasien);
        // if (pasien.isPresent()) {
        // } 
    }

}
