package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmailVerification(Customer customer) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Seu código de verificação");
        simpleMailMessage.setText("Seu código de verificação é: " + customer.getEmailVerificationCode());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendDriverLicenseApproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua CNH Foi Aprovada.");
        simpleMailMessage.setText("Parabéns! Seu documento enviado foi aprovado. \nPedimos gentilmente que não responda a este e-mail. \nCaso tenha mais alguma dúvida ou necessite de assistência, por favor, entre em contato pelos canais apropriados. Agradecemos pela sua cooperação.");

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendDriverLicenseDisapprove(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua CNH Foi Reprovada.");
        simpleMailMessage.setText("Lamentamos informar que o documento enviado não foi aprovado. \nSe precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados. Agradecemos pela sua compreensão. \n\n Abaixo mais detalhes do porque foi reprovado: \n\n" + fileApproved.getMessage());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendIdentityNumberApproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Identidade Foi Aprovada.");
        simpleMailMessage.setText("Parabéns! Seu documento enviado foi aprovado. \nPedimos gentilmente que não responda a este e-mail. \nCaso tenha mais alguma dúvida ou necessite de assistência, por favor, entre em contato pelos canais apropriados. Agradecemos pela sua cooperação.");

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendIdentityNumberReproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Identidade Foi Reprovada.");
        simpleMailMessage.setText("Lamentamos informar que o documento enviado não foi aprovado. \nSe precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados. Agradecemos pela sua compreensão. \n\n Abaixo mais detalhes do porque foi reprovado: \n\n" + fileApproved.getMessage());

        javaMailSender.send(simpleMailMessage);
    }
}