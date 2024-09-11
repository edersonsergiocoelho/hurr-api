package br.com.escconsulting.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public class ErrorResponse {

    private String message;

    @Autowired
    private MessageSource messageSource;

    public ErrorResponse(String messageKey) {
        Locale locale = LocaleContextHolder.getLocale();
        this.message = messageSource.getMessage(messageKey, null, locale);
    }

    public String getMessage() {
        return message;
    }
}