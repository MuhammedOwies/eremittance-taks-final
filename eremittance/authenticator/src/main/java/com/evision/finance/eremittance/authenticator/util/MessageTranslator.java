package com.evision.finance.eremittance.authenticator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageTranslator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    MessageTranslator(ResourceBundleMessageSource messageSource) {
        MessageTranslator.messageSource = messageSource;
    }

    public static String toLocale(String msgCode, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, args, locale);
    }
}
