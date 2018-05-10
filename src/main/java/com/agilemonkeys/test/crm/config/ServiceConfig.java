package com.agilemonkeys.test.crm.config;

import com.agilemonkeys.test.crm.util.ModelMapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ModelMapperUtil modelMapper() {
        return new ModelMapperUtil();
    }
}
