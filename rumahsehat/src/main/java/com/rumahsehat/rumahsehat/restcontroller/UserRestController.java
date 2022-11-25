package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.service.UserRestService;
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
public class UserRestController {

    @Autowired
    private UserRestService userRestService;

    @PostMapping(value = "/user/add", consumes = "application/json")
    private UserModel createUser(@Valid @RequestBody UserModel user, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field. "
            );
        } else {
            return userRestService.createUser(user);
        }
    }
}
