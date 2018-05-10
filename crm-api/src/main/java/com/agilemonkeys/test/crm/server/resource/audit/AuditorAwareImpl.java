package com.agilemonkeys.test.crm.server.resource.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

    public static final String NO_USER_LOGGER = "NO_USER_LOGGER";

    @Override
    public String getCurrentAuditor() {
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return NO_USER_LOGGER;

    }
}
