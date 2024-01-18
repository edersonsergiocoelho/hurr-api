package br.com.escconsulting.controller;

import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.service.*;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    private final EmailService emailService;

    private TelegramBot telegramBot;

    private final TwilioService twilioService;

    private final WhatsAppService whatsAppService;

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

    @PostMapping("emailVerificationCode")
    public ResponseEntity<Customer> emailVerificationCode(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.emailVerificationCode(customer);

        if (savedCustomer != null && savedCustomer.getEmailVerificationCode() != null) {
            emailService.sendEmailVerification(savedCustomer);
        }

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("emailValidateCode")
    public ResponseEntity<Customer> emailValidateCode(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.emailValidateCode(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PostMapping("phoneVerificationCodeSMS")
    public ResponseEntity<Customer> phoneVerificationCodeSMS(@RequestBody Customer customer) {
        Message message = customerService.phoneVerificationCodeSMS(customer);

        if (message != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("phoneVerificationCodeTelegram")
    public ResponseEntity<Customer> phoneVerificationCodeTelegram(@RequestBody Customer customer) throws URISyntaxException, IOException, InterruptedException {

        // Envia o código de verificação
        //telegramBot.sendCode("+5511989745259", "seu_código_de_verificação");
        whatsAppService.sendMessage("5511989745259", "Teste de Mensagem");
        whatsAppService.sendTemplateMessage("5511989745259", "A1B2C3");

        // Retorna o cliente
        return ResponseEntity.ok(customer);

    }

    @PostMapping("phoneVerificationCodeWhatsApp")
    public ResponseEntity<Customer> phoneVerificationCodeWhatsApp(@RequestBody Customer customer) {
        Message message = customerService.phoneVerificationCodeWhatsApp(customer);

        if (message != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("phoneValidateCode")
    public ResponseEntity<Customer> phoneValidateCode(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.phoneValidateCode(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
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