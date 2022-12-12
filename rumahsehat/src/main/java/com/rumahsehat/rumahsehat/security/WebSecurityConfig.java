package com.rumahsehat.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/api/v1/**").permitAll()
//                .antMatchers("/user/viewall").hasAuthority("admin")
                .antMatchers("/obat/viewall").hasAnyAuthority("admin", "Apoteker")
                .antMatchers("/obat/ubah/**").hasAuthority("Apoteker")
                .antMatchers("/appointment").hasAnyAuthority("admin", "Dokter")
                .antMatchers("/appointment/*").hasAnyAuthority("admin", "Dokter")
                .antMatchers("/user/manajemen-user").hasAuthority("admin")
                .antMatchers("/user/add-dokter").hasAuthority("admin")
                .antMatchers("/user/viewall-dokter").hasAuthority("admin")
                .antMatchers("/user/add-apoteker").hasAuthority("admin")
                .antMatchers("/create-resep/**").hasAuthority("Dokter")
                .antMatchers("/detail-resep/**").hasAnyAuthority("admin", "Dokter", "pasien", "Apoteker" )
                .antMatchers("/viewall-apoteker").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

//@Configuration
//@EnableWebSecurity
//@Slf4j
//public class WebSecurityConfig{
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Resource
//    private CustomAuthenticationFilter customAuthenticationFilter;
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login-sso", "/validate-ticket").permitAll()
//                .antMatchers("/user/viewall").hasAuthority("admin")
//                .antMatchers("/obat/viewall").hasAuthority("admin")
//                .antMatchers("/obat/viewall").hasAuthority("apoteker")
//                .antMatchers("/obat/ubah/**").hasAuthority("apoteker")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").permitAll();
//        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception{
////        return super.authenticationManagerBean();
////    }
//
//    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("sena").password(encoder.encode("apapABC")).roles("USER");
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
////         System.out.println("INSIDE CONFIG AUTHENTICATION");
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//    }
//
//}

