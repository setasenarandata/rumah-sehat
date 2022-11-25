package com.rumahsehat.rumahsehat.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/login-sso", "/validate-ticket").permitAll()
        .antMatchers("/api/v1/user/add").permitAll()
        .antMatchers("/user/viewall").hasAuthority("admin")
        .antMatchers("/obat/viewall").hasAuthority("admin")
        .antMatchers("/obat/viewall").hasAuthority("apoteker")
        .antMatchers("/obat/ubah/**").hasAuthority("apoteker")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login").permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").permitAll();

        return http.build();
    }

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("sena").password(encoder.encode("apapABC")).roles("USER");
    }

     @Autowired
     private UserDetailsService userDetailsService;

     @Autowired
     public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//         System.out.println("INSIDE CONFIG AUTHENTICATION");
         auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
     }

}
