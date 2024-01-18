package br.com.escconsulting.service.impl;

import br.com.escconsulting.config.TwilioConfig;
import br.com.escconsulting.service.TwilioService;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioServiceImpl implements TwilioService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        this.twilioConfig.init();
    }

    @Override
    public Message sendSMS(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                            new PhoneNumber(toPhoneNumber),
                            new PhoneNumber(twilioConfig.getPhoneNumber()),
                            messageBody)
                    .create();

            return message;

        } catch (TwilioException e) {
            System.err.println("Erro ao enviar SMS: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Message sendWhatsApp(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + toPhoneNumber),
                            new PhoneNumber("whatsapp:" + twilioConfig.getPhoneWhatsApp()),
                            messageBody)
                    .create();

            return message;

        } catch (TwilioException e) {
            System.err.println("Erro ao enviar WhatsApp: " + e.getMessage());
        }

        return null;
    }
}