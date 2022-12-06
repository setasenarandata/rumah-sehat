package com.rumahsehat.rumahsehat.security;

import com.rumahsehat.rumahsehat.model.UserModel;
import com.rumahsehat.rumahsehat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AdminDb adminDb;
    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    PasienDb pasienDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = adminDb.findByUsername(username);
        if(user == null) {
            user = apotekerDb.findByUsername(username);
        }
        if(user == null) {
            user = dokterDb.findByUsername(username);
        }
        if(user == null) {
            user = pasienDb.findByUsername(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
