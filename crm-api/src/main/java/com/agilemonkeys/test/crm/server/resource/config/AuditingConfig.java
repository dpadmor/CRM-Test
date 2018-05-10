package com.agilemonkeys.test.crm.server.resource.config;

import com.agilemonkeys.test.crm.server.resource.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware <String> auditorProvider () {
        return new AuditorAwareImpl();
    }



}
