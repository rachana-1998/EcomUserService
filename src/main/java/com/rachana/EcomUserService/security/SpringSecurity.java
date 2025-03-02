package com.rachana.EcomUserService.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurity {

//    @Order(1)
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors().disable();
//        http.csrf().disable();
//        http.authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll());
//
//        //  http.authorizeHttpRequests(authrize-> authrize.requestMatchers("/auth/*").permitAll());
//        //http.authorizeHttpRequests(authrize-> authrize.requestMatchers("/order/*").authenticated());
//        return http.build();
//    }
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }


}
