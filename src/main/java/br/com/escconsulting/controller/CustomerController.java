package br.com.escconsulting.controller;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final EmailService emailService;

    @Autowired
    public CustomerController(CustomerService customerService, EmailService emailService) {
        this.customerService = customerService;
        this.emailService = emailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") UUID id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/by/email/{email}")
    public ResponseEntity<Customer> findByEmail(@PathVariable("email") String email) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(email);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> listCustomer = customerService.findAll();
        return ResponseEntity.ok(listCustomer);
    }

    @PostMapping("sendCode")
    public ResponseEntity<Customer> sendCode(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.sendCode(customer);

        if (savedCustomer != null && savedCustomer.getEmailVerificationCode() != null) {
            emailService.sendEmailVerification(savedCustomer);
        }

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}