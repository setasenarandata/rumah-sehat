package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.UserDb;
import com.rumahsehat.rumahsehat.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

    private final WebClient webClient;

    @Autowired
    private UserDb userDb;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.userUrl).build();
    }

    @Override
    public UserModel createUser(UserModel user) {
        return userDb.save(user);
    }
}
