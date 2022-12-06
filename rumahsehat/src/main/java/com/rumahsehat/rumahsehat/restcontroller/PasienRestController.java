package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PasienRestController {

    @Autowired
    private PasienRestService pasienRestService;

    @PostMapping(value = "/pasien/add", consumes = "application/json")
    private PasienModel addPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field. "
            );
        } else {
            return pasienRestService.addPasien(pasien);
        }
    }

    @PatchMapping(value = "/pasien/{id}/topup/{amount}")
    private boolean topupSaldo(@PathVariable("id") String id, @PathVariable("amount") int amount){
        PasienModel pasien = pasienRestService.getPasienById(id).get();
        boolean isSuccess = pasienRestService.topupSaldo(pasien, amount);

        return isSuccess ? true : false;
    }

    @GetMapping(value = "/pasien/{id}")
    private PasienModel getPasienById(@PathVariable("id") String id) {
        try {
            System.out.println("ENTER GET MAPPING");
            System.out.println("ID: " + id);
            return pasienRestService.getPasienById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pasien dengan id " + id + " tidak ditemukan"
            );
        }
    }
}
