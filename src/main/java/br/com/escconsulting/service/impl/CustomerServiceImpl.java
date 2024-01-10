package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.repository.CustomerRepository;
import br.com.escconsulting.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
    public Customer sendCode(Customer customer) {

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

    public class RandomCodeGenerator {

        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private static SecureRandom random = new SecureRandom();

        public static String generateCode(int length) {
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                code.append(CHARACTERS.charAt(randomIndex));
            }
            return code.toString();
        }

        public static void main(String[] args) {
            String generatedCode = generateCode(6);
            System.out.println("Generated Code: " + generatedCode);
        }
    }
}