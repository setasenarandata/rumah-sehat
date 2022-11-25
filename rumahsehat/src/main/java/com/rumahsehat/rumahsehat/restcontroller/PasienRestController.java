package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
}
