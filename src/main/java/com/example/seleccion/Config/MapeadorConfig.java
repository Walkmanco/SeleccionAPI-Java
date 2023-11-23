package com.example.seleccion.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapeadorConfig {
    @Bean("DefaultMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
