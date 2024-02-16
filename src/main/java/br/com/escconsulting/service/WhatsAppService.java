package br.com.escconsulting.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WhatsAppService {

    void sendMessage(String toPhoneNumber, String messageContent) throws URISyntaxException, IOException, InterruptedException;

    void sendTemplateMessage(String toPhoneNumber, String code) throws URISyntaxException, IOException, InterruptedException;
}