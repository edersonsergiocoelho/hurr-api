package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.repository.CustomerRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.TwilioService;
import br.com.escconsulting.util.RandomCodeGenerator;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final TwilioService twilioService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TwilioService twilioService) {
        this.customerRepository = customerRepository;
        this.twilioService = twilioService;
    }

    @Override
    public Customer findById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer emailVerificationCode(Customer customer) {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());

        if (optionalCustomer.isPresent()) {

            Customer customerUpdate = optionalCustomer.get();

            customerUpdate.setEmailVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());

            return customerRepository.save(customerUpdate);

        } else {

            customer.setCreatedDate(Instant.now());
            customer.setEmailVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());
            customer.setEnabled(Boolean.TRUE);
            customer.setCustomerType(CustomerType.CUSTOMER);

            return customerRepository.save(customer);
        }
    }

    public Customer emailValidateCode(Customer customer) {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());

        if (optionalCustomer.isPresent()) {

            Customer customerUpdate = optionalCustomer.get();

            if (customer.getEmailVerificationCode().equals(customerUpdate.getEmailVerificationCode())) {
                customerUpdate.setEmailValidated(Boolean.TRUE);
                customerUpdate.setEmailVerificationCode("");

                return customerRepository.save(customerUpdate);
            }

            return null;
        }

        return null;
    }

    @Override
    public Message phoneVerificationCodeSMS(Customer customer) {

        String message = "O seu código de verificação para Hurr é: " + RandomCodeGenerator.generateCode(6).toUpperCase();

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());

        if (optionalCustomer.isPresent()) {

            Customer customerUpdate = optionalCustomer.get();

            customerUpdate.setPhoneVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());

            customerRepository.save(customerUpdate);
        }

        return twilioService.sendSMS(customer.getDdiPhone() + customer.getPhone(), message);
    }

    @Override
    public Message phoneVerificationCodeWhatsApp(Customer customer) {

        String message = "O seu código de verificação para Hurr é: " + RandomCodeGenerator.generateCode(6).toUpperCase();

        return twilioService.sendWhatsApp(customer.getDdiPhone() + customer.getPhone(), message);
    }

    public Customer phoneValidateCode(Customer customer) {

        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());

        if (optionalCustomer.isPresent()) {

            Customer customerUpdate = optionalCustomer.get();

            if (customer.getPhoneVerificationCode().equals(customerUpdate.getPhoneVerificationCode())) {
                customerUpdate.setPhoneValidated(Boolean.TRUE);
                customerUpdate.setPhoneVerificationCode("");

                return customerRepository.save(customerUpdate);
            }

            return null;
        }

        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(UUID id, Customer review) {
        Customer existingReview = findById(id);

        return customerRepository.save(existingReview);
    }

    @Override
    public void delete(UUID id) {
        Customer review = findById(id);
        customerRepository.delete(review);
    }
}