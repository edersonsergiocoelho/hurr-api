package br.com.escconsulting.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class APIResponseFactory {

    @Autowired
    private MessageSource messageSource;

    public ErrorResponse createAPIResponse(String messageKey) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(messageKey, null, locale);
        return new ErrorResponse(message);
    }
}