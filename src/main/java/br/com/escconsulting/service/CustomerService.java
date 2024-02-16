package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.FileApproved;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<Customer> findById(UUID id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();

    Optional<Customer> emailVerificationCode(Customer customer);

    Optional<Customer> emailValidateCode(Customer customer);

    Optional<Message> phoneVerificationCodeSMS(Customer customer);

    Optional<Customer> phoneVerificationCodeWhatsApp(Customer customer);

    Optional<Customer> phoneValidateCode(Customer customer);

    Optional<FileApproved> uploadIdentityNumber(LocalUser localUser, MultipartFile[] files) throws IOException;

    Optional<FileApproved> uploadDriverLicense(LocalUser localUser, MultipartFile[] files) throws IOException;

    Optional<Customer> save(Customer customer);

    Optional<Customer> update(UUID id, Customer customer);

    void delete(UUID id);
}