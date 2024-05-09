package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
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
    public void sendForgotPasswordVerificationCode(User user) {

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Você está recebendo este e-mail porque solicitou a recuperação de senha para sua conta na HURR.\n\n");
        emailBody.append("Seu código de verificação é: ").append(user.getForgotPasswordVerificationCode()).append("\n\n");
        emailBody.append("Por favor, utilize este código para redefinir sua senha. Este código é válido apenas por um curto período de tempo.\n\n");
        emailBody.append("Se você não solicitou a recuperação de senha, pode ignorar este e-mail com segurança.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Recuperação de senha - Código de verificação");
        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendForgotPasswordValidated(User user) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Este é um e-mail de confirmação para informar que o código de recuperação de senha associado à sua conta na HURR foi validado com sucesso.\n\n");
        emailBody.append("Por favor, continue o processo de recuperação de senha conforme necessário.\n\n");
        emailBody.append("Se você não solicitou a recuperação de senha ou se a validação foi realizada por engano, entre em contato conosco imediatamente.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Recuperação de senha - Código validado com sucesso");
        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendForgotPassword(User user) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Este é um e-mail de confirmação para informar que a sua senha foi alterada com sucesso na HURR.\n\n");
        emailBody.append("Se você não realizou esta alteração ou se tiver alguma dúvida, entre em contato conosco imediatamente.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Senha Alterada Com Sucesso");
        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailVerificationCode(Customer customer) {
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