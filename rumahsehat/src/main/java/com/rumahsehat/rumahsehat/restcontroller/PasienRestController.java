package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.service.PasienRestService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import javax.validation.Valid;


@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class PasienRestController {

    @Autowired
    private PasienRestService pasienRestService;


    @PostMapping(value = "/pasien/add", consumes = "application/json")
    private PasienModel addPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            log.error("HasFieldErrors has occured");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field. "
            );
        } else {
            log.info("Pasien added successfully");
            return pasienRestService.addPasien(pasien);
        }
    }

    @PatchMapping(value = "/pasien/{username}/topup/{amount}")
    private boolean topupSaldo(@PathVariable("username") String username, @PathVariable("amount") int amount){
        log.info("Initating topup for pasien: " + username);
        PasienModel pasien = pasienRestService.getPasienByUsername(username);
        boolean isSuccess = pasienRestService.topupSaldo(pasien, amount);
        if (isSuccess) {
            log.info("Topup to patient balance is success!");
        } else {
            log.error("There's an error while adding balance to patient: " + username);
        }
        return isSuccess ? true : false;
    }

    @GetMapping(value = "/pasien/{username}")
    private PasienModel getPasienById(@PathVariable("username") String username) {
        try {
            System.out.println("ENTER GET MAPPING");
            System.out.println("username: " + username);
            log.info("Initiating getPasienById");
            log.info("Targeted username: " + username);
            return pasienRestService.getPasienByUsername(username);
        } catch (NoSuchElementException e) {
            log.error("Error has occured. Cannot find patient.");
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan"
            );
        }
    }@PutMapping(value = "/pasien/{username}/bayarTagihan/{amount}")
    private PasienModel bayarTagihan(@PathVariable("username") String username, @PathVariable int amount){
        try{
            return pasienRestService.bayarTagihan(username, amount);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan username " + username + " tidak ditemukan"
            );
        }

    }

}
