package br.com.escconsulting.config;

import com.twilio.Twilio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSID;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @Value("${twilio.phone.whatsapp}")
    private String phoneWhatsApp;

    public void init() {
        Twilio.init(accountSID, authToken);
    }
}