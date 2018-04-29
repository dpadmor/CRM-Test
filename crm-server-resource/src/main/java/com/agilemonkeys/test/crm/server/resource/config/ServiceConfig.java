package com.agilemonkeys.test.crm.server.resource.config;


import com.agilemonkeys.test.crm.server.resource.util.ModelMapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ModelMapperUtil modelMapper() {
        return new ModelMapperUtil();
    }
}
