package br.com.escconsulting.service;

import br.com.escconsulting.entity.Customer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailVerification(Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("Seu código de verificação");
        simpleMailMessage.setText("Seu código de verificação é: " + customer.getEmailVerificationCode());

        javaMailSender.send(simpleMailMessage);
    }
}