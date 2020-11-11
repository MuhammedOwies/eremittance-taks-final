package com.evision.finance.eremittance.authenticator.entity.audit.listener;

import com.evision.finance.eremittance.authenticator.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Utils.getPrincipal());
    }
}
