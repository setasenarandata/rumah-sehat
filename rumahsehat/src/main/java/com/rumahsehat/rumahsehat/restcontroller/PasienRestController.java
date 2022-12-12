package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
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

    @PatchMapping(value = "/pasien/{username}/topup/{amount}")
    private boolean topupSaldo(@PathVariable("username") String username, @PathVariable("amount") int amount){
        PasienModel pasien = pasienRestService.getPasienByUsername(username);
        boolean isSuccess = pasienRestService.topupSaldo(pasien, amount);

        return isSuccess ? true : false;
    }

    @GetMapping(value = "/pasien/{username}")
    private PasienModel getPasienById(@PathVariable("username") String username) {
        try {
            System.out.println("ENTER GET MAPPING");
            System.out.println("username: " + username);
            return pasienRestService.getPasienByUsername(username);
        } catch (NoSuchElementException e) {
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
