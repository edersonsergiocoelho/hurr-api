package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return customerService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    /*
    @PostMapping("emailVerificationCode")
    public ResponseEntity<?> emailVerificationCode(@RequestBody Customer customer) {
        return customerService.emailVerificationCode(customer)
                .map(Optional::of)
                .orElseThrow(() -> new IllegalStateException("Failed to create customer"))
                .flatMap(savedCustomer -> {
                    if (savedCustomer.getEmailVerificationCode() != null) {
                        emailService.sendEmailVerification(savedCustomer);
                        return Optional.of(savedCustomer);
                    } else {
                        return Optional.empty();
                    }
                })
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to send verification code"));
    }
    */

    @PostMapping("emailVerificationCode")
    public ResponseEntity<?> emailVerificationCode(@RequestBody Customer customer) {
        return customerService.emailVerificationCode(customer)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to create customer or send verification code"));
    }

    @PostMapping("emailValidateCode")
    public ResponseEntity<?> emailValidateCode(@RequestBody Customer customer) {
        return customerService.emailValidateCode(customer)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to validate email"));
    }

    @PostMapping("phoneVerificationCodeSMS")
    public ResponseEntity<?> phoneVerificationCodeSMS(@RequestBody Customer customer) {
        return customerService.phoneVerificationCodeSMS(customer)
                .map(message -> ResponseEntity.ok().build())
                .orElseThrow(() -> new IllegalStateException("Failed to send SMS verification code"));
    }

    @PostMapping("phoneVerificationCodeTelegram")
    public ResponseEntity<?> phoneVerificationCodeTelegram(@RequestBody Customer customer) throws URISyntaxException, IOException, InterruptedException {

        // Envia o código de verificação
        //telegramBot.sendCode("+5511989745259", "seu_código_de_verificação");
        //whatsAppService.sendMessage("5511989745259", "Teste de Mensagem");
        //whatsAppService.sendTemplateMessage("5511989745259", "A1B2C3");

        // Retorna o cliente
        return ResponseEntity.ok(customer);

    }

    @PostMapping("phoneVerificationCodeWhatsApp")
    public ResponseEntity<?> phoneVerificationCodeWhatsApp(@RequestBody Customer customer) {
        return customerService.phoneVerificationCodeWhatsApp(customer)
                .map(message -> ResponseEntity.ok().build())
                .orElseThrow(() -> new IllegalStateException("Failed to send verification code"));
    }

    @PostMapping("phoneValidateCode")
    public ResponseEntity<?> phoneValidateCode(@RequestBody Customer customer) {
        return customerService.phoneValidateCode(customer)
                .map(ResponseEntity::ok)  // Create 200 OK response with saved customer
                .orElseThrow(() -> new IllegalStateException("Failed to validate phone code"));
    }

    @PostMapping("uploadIdentityNumber")
    public ResponseEntity<?> uploadIdentityNumber(@CurrentUser LocalUser user, @RequestParam("file") MultipartFile[] files) throws IOException {
        return customerService.uploadIdentityNumber(user, files)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to upload identity number"));
    }

    @PostMapping("uploadDriverLicense")
    public ResponseEntity<?> uploadDriverLicense(@CurrentUser LocalUser user, @RequestParam("file") MultipartFile[] files) throws IOException {
        return customerService.uploadDriverLicense(user, files)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to upload driver license"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        return customerService.save(customer)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        return customerService.update(id, customer)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}