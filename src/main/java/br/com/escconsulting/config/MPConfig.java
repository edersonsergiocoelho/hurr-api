package br.com.escconsulting.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MPConfig {

    @Value("${mercado.pago.access.token}")
    private String accessToken;
}