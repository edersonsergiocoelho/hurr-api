package br.com.escconsulting.service;

import br.com.escconsulting.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Customer findById(UUID id);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();

    Customer sendCode(Customer customer);

    Customer save(Customer customer);

    Customer update(UUID id, Customer customer);

    void delete(UUID id);
}