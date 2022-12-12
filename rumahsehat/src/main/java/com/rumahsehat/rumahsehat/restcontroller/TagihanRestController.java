package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.TagihanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rumahsehat.rumahsehat.service.TagihanRestService;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class TagihanRestController {
    @Autowired
    private TagihanRestService tagihanRestService;

    @GetMapping(value = "/tagihan/{kode}")
    private TagihanModel getTagihanByIdPasienAndKodeTagihan(
            @PathVariable("kode") String kode
    ) {
        try{
            return tagihanRestService.getTagihanByKode(kode);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }

    @PutMapping(value="/tagihan/{kode}")
    private TagihanModel updateStatusTagihan(@PathVariable("kode") String kode, @RequestBody TagihanModel tagihanUpdate){
        try{
            return tagihanRestService.updateStatusTagihan(kode, tagihanUpdate);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }

}
