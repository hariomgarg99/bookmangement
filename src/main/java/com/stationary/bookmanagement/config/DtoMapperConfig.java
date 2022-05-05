package com.stationary.bookmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper;
    }
}
