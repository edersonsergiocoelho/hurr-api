package br.com.escconsulting.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public class APIResponse {

    private Boolean success;
    private String message;

    @Autowired
    private MessageSource messageSource;

    // Construtor onde apenas a chave da mensagem é passada
    public APIResponse(Boolean success, String messageKey) {
        this.success = success;
        // Pega o Locale padrão (ou um definido externamente)
        Locale locale = LocaleContextHolder.getLocale();
        // Resolve a mensagem com base na chave e no Locale atual
        this.message = messageSource.getMessage(messageKey, null, locale);
    }
}