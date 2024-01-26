package br.com.escconsulting.service;

import com.twilio.rest.api.v2010.account.Message;

public interface TwilioService {
    Message sendSMS(String toPhoneNumber, String messageBody);
    Message sendWhatsApp(String toPhoneNumber, String messageBody);
}