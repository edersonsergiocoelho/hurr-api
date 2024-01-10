package br.com.escconsulting.service;

import br.com.escconsulting.entity.Customer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmailVerification(Customer customer) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(customer.getEmail());
            message.setSubject("Seu código de verificação");
            message.setText("Seu código de verificação é: " + customer.getEmailVerificationCode());

            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}