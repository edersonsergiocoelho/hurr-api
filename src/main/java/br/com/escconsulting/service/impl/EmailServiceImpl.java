package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.*;
import br.com.escconsulting.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendForgotPasswordVerificationCode(User user) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Recuperação de senha - Código de verificação");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Você está recebendo este e-mail porque solicitou a recuperação de senha para sua conta na HURR.\n\n");
        emailBody.append("Seu código de verificação é: ").append(user.getForgotPasswordVerificationCode()).append("\n\n");
        emailBody.append("Por favor, utilize este código para redefinir sua senha. Este código é válido apenas por um curto período de tempo.\n\n");
        emailBody.append("Se você não solicitou a recuperação de senha, pode ignorar este e-mail com segurança.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendForgotPasswordValidated(User user) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Recuperação de senha - Código validado com sucesso");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Este é um e-mail de confirmação para informar que o código de recuperação de senha associado à sua conta na HURR foi validado com sucesso.\n\n");
        emailBody.append("Por favor, continue o processo de recuperação de senha conforme necessário.\n\n");
        emailBody.append("Se você não solicitou a recuperação de senha ou se a validação foi realizada por engano, entre em contato conosco imediatamente.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendForgotPassword(User user) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Senha Alterada Com Sucesso");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Este é um e-mail de confirmação para informar que a sua senha foi alterada com sucesso na HURR.\n\n");
        emailBody.append("Se você não realizou esta alteração ou se tiver alguma dúvida, entre em contato conosco imediatamente.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailVerificationCode(Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Seu código de verificação");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá\n\n");
        emailBody.append("Aqui está o seu código de verificação para acessar sua conta no HURR:\n\n");
        emailBody.append("Código de verificação: ").append(customer.getEmailVerificationCode()).append("\n\n");
        emailBody.append("Por favor, insira este código na página de verificação para concluir o processo de registro.\n\n");
        emailBody.append("Se você não solicitou este código, por favor, ignore este e-mail.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendDriverLicenseApproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua CNH Foi Aprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá\n\n");
        emailBody.append("Parabéns! Sua Carteira Nacional de Habilitação (CNH) foi aprovada.\n\n");
        emailBody.append("Pedimos gentilmente que não responda a este e-mail.\n");
        emailBody.append("Caso tenha mais alguma dúvida ou necessite de assistência, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua cooperação.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendDriverLicenseDisapprove(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua CNH Foi Reprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá\n\n");
        emailBody.append("Lamentamos informar que sua Carteira Nacional de Habilitação (CNH) não foi aprovada.\n");
        emailBody.append("Se precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua compreensão.\n\n");
        emailBody.append("Motivo da reprovação:\n").append(fileApproved.getMessage()).append("\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendIdentityNumberApproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Identidade Foi Aprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá\n\n");
        emailBody.append("Parabéns! Seu documento de identidade foi aprovado.\n\n");
        emailBody.append("Pedimos gentilmente que não responda a este e-mail.\n");
        emailBody.append("Caso tenha mais alguma dúvida ou necessite de assistência, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua cooperação.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendIdentityNumberReproved(FileApproved fileApproved, Customer customer) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Identidade Foi Reprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá\n\n");
        emailBody.append("Lamentamos informar que o documento de identidade enviado não foi aprovado.\n");
        emailBody.append("Se precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua compreensão.\n\n");
        emailBody.append("Motivo da reprovação:\n").append(fileApproved.getMessage()).append("\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendProfilePictureApproved(FileApproved fileApproved, User user) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Foto de Perfil Foi Aprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Parabéns! Sua foto de perfil foi aprovada.\n\n");
        emailBody.append("Pedimos gentilmente que não responda a este e-mail.\n");
        emailBody.append("Caso tenha mais alguma dúvida ou necessite de assistência, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua cooperação.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendProfilePictureReproved(FileApproved fileApproved, User user) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("HURR - Sua Foto de Perfil Foi Reprovada.");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(user.getDisplayName()).append(",\n\n");
        emailBody.append("Lamentamos informar que sua foto de perfil não foi aprovada.\n");
        emailBody.append("Se precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua compreensão.\n\n");
        emailBody.append("Motivo da reprovação:\n").append(fileApproved.getMessage()).append("\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendCustomerWithdrawalRequestApproval(CustomerWithdrawalRequest customerWithdrawalRequest) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customerWithdrawalRequest.getCustomer().getEmail());
        simpleMailMessage.setSubject("HURR - Seu Pagamento Foi Efetuado");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(customerWithdrawalRequest.getCustomer().getFirstName()).append(",\n\n");
        emailBody.append("Temos o prazer de informar que seu pagamento foi efetuado com sucesso.\n\n");
        emailBody.append("Detalhes do pagamento:\n");
        emailBody.append("Banco: ").append(customerWithdrawalRequest.getCustomerBankAccount().getBank().getBankName()).append("\n");
        emailBody.append("PIX: ").append(customerWithdrawalRequest.getCustomerBankAccount().getPixKey()).append("\n");
        emailBody.append("Método de Pagamento: ").append(customerWithdrawalRequest.getPaymentMethod().getPaymentMethodName()).append("\n");
        emailBody.append("Status do Pagamento: ").append(customerWithdrawalRequest.getPaymentStatus().getPaymentStatusName()).append("\n");
        emailBody.append("Data da Retirada: ").append(customerWithdrawalRequest.getWithdrawalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))).append("\n\n");
        emailBody.append("Se precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua confiança.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendCustomerVehicleCreated(CustomerVehicle customerVehicle) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(customerVehicle.getCustomer().getEmail());
        simpleMailMessage.setSubject("HURR - Cadastro De Veículo Efetuado Com Sucesso");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá ").append(customerVehicle.getCustomer().getFirstName()).append(",\n\n");
        emailBody.append("Seu cadastro de veículo foi efetuado com sucesso!\n\n");
        emailBody.append("Detalhes do veículo:\n");
        emailBody.append("Modelo: ").append(customerVehicle.getVehicleModel().getVehicleModelName()).append("\n");
        emailBody.append("Cor: ").append(customerVehicle.getVehicleColor().getVehicleColorName()).append("\n");
        emailBody.append("Placa: ").append(customerVehicle.getLicensePlate()).append("\n\n");
        emailBody.append("Seu cadastro passará por uma aprovação que pode demorar até 3 dias, durante a qual verificaremos os documentos do veículo.\n");
        emailBody.append("Você receberá um e-mail informando se o cadastro foi aprovado ou não. Caso seja aprovado, você poderá continuar o cadastro para poder alugar o carro na nossa plataforma.\n");
        emailBody.append("Se o cadastro for reprovado, você receberá um e-mail com a justificativa e terá que enviar o cadastro novamente.\n\n");
        emailBody.append("Se precisar de esclarecimentos adicionais ou se tiver dúvidas, por favor, entre em contato pelos canais apropriados.\n");
        emailBody.append("Agradecemos pela sua confiança.\n\n");
        emailBody.append("Atenciosamente,\n");
        emailBody.append("Equipe HURR");

        simpleMailMessage.setText(emailBody.toString());

        javaMailSender.send(simpleMailMessage);
    }

}