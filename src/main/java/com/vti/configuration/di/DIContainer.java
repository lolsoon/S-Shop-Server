package com.vti.configuration.di;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DIContainer {
    @Bean
    public ModelMapper provideModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
