package br.com.escconsulting.config;

import inter.InterSdk;
import inter.exceptions.SdkException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class InterConfig {

    @Value("${inter.client.id}")
    private String clientId;

    @Value("${inter.client.secret}")
    private String clientSecret;

    @Value("${inter.cert.path}")
    private String certPath;

    @Value("${inter.cert.password}")
    private String certPassword;

    @Bean
    public InterSdk interSdk() throws SdkException {

        InterSdk interSdk = null;

        try {

            interSdk = new InterSdk(
                    clientId,
                    clientSecret,
                    certPath,
                    certPassword
            );

            interSdk.setAmbiente("sandbox");
            interSdk.setDebug(true);
            interSdk.setContaCorrente("3559391-1");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return interSdk;
    }
}