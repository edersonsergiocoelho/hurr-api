package br.com.escconsulting.service;

import br.com.escconsulting.entity.Customer;
import com.twilio.rest.api.v2010.account.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer findById(UUID id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();

    Customer emailVerificationCode(Customer customer);

    Customer emailValidateCode(Customer customer);

    Message phoneVerificationCodeSMS(Customer customer);

    Message phoneVerificationCodeWhatsApp(Customer customer);

    Customer phoneValidateCode(Customer customer);

    Customer save(Customer customer);

    Customer update(UUID id, Customer customer);

    void delete(UUID id);
}