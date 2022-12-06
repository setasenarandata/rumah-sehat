package com.rumahsehat.rumahsehat.restcontroller;

import com.rumahsehat.rumahsehat.model.PasienModel;
import com.rumahsehat.rumahsehat.model.TagihanModel;
import com.rumahsehat.rumahsehat.service.TagihanRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class TagihanRestController {

    @Autowired
    private TagihanRestServiceImpl tagihanRestService;

    @GetMapping(value = "/tagihan/{pasienId}")
    private List<TagihanModel> getTagihanByPasienId(@PathVariable("pasienId") String pasienId) {
       return tagihanRestService.findAllByPasien(pasienId);
    }
}
