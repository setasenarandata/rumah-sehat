package com.rumahsehat.rumahsehat.service;

import com.rumahsehat.rumahsehat.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> grantedAuthorities = user.getAuthorities();
        Optional<GrantedAuthority> options = grantedAuthorities.stream().findFirst();
        GrantedAuthority auth;
        if (options.isPresent()) {
            auth = options.get();
            return auth.getAuthority();
        } else return null;
    }

}
