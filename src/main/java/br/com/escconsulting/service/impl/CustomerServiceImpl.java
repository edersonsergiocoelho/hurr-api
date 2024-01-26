package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.repository.CustomerRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.FileApprovedService;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.TwilioService;
import br.com.escconsulting.util.RandomCodeGenerator;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final TwilioService twilioService;

    private final FileService fileService;

    private final FileApprovedService fileApprovedService;

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id)));
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
    public Optional<Customer> emailVerificationCode(Customer customer) {
        return Optional.of(customerRepository.findByEmail(customer.getEmail())
                .map(customerToUpdate -> {
                    customerToUpdate.setEmailVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());
                    return customerRepository.save(customerToUpdate);
                })
                .orElseGet(() -> {
                    customer.setCreatedDate(Instant.now());
                    customer.setEmailVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());
                    customer.setEnabled(Boolean.TRUE);
                    customer.setCustomerType(CustomerType.CUSTOMER);
                    return customerRepository.save(customer);
                }));
    }

    @Override
    public Optional<Customer> emailValidateCode(Customer customer) {
        return customerRepository.findByEmail(customer.getEmail())
                .flatMap(customerToUpdate -> {
                    if (customer.getEmailVerificationCode().equals(customerToUpdate.getEmailVerificationCode())) {
                        customerToUpdate.setEmailValidated(true);
                        customerToUpdate.setEmailVerificationCode("");
                        return Optional.of(customerRepository.save(customerToUpdate));
                    } else {
                        return Optional.empty();
                    }
                });
    }

    @Override
    public Optional<Message> phoneVerificationCodeSMS(Customer customer) {
        String message = "O seu código de verificação para Hurr é: " + RandomCodeGenerator.generateCode(6).toUpperCase();

        return customerRepository.findByEmail(customer.getEmail())
                .map(customerToUpdate -> {
                    customerToUpdate.setPhoneVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());
                    return customerRepository.save(customerToUpdate);
                })
                .flatMap(customerUpdated -> Optional.ofNullable(twilioService.sendSMS(customer.getDdiPhone() + customer.getPhone(), message)));
    }

    @Override
    public Optional<Message> phoneVerificationCodeWhatsApp(Customer customer) {
        String message = "O seu código de verificação para Hurr é: " + RandomCodeGenerator.generateCode(6).toUpperCase();
        Message sentMessage = twilioService.sendWhatsApp(customer.getDdiPhone() + customer.getPhone(), message);
        return Optional.ofNullable(sentMessage);
    }

    @Override
    public Optional<Customer> phoneValidateCode(Customer customer) {
        return customerRepository.findByEmail(customer.getEmail())
                .flatMap(customerToUpdate -> {
                    if (customer.getPhoneVerificationCode().equals(customerToUpdate.getPhoneVerificationCode())) {
                        customerToUpdate.setPhoneValidated(true);
                        customerToUpdate.setPhoneVerificationCode("");
                        return Optional.of(customerRepository.save(customerToUpdate));
                    } else {
                        return Optional.empty();
                    }
                });
    }

    @Override
    public void uploadHandler(LocalUser localUser, MultipartFile[] files) throws IOException {
        customerRepository.findByEmail(localUser.getUser().getEmail())
                .ifPresent(customer -> {
                    MultipartFile multipartFile = Arrays.stream(files).findFirst().get();

                    File file = new File();
                    file.setContentType(multipartFile.getContentType());
                    file.setOriginalFileName(multipartFile.getOriginalFilename());
                    try {
                        file.setDataAsByteArray(multipartFile.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    file.setCreatedDate(Instant.now());
                    file.setEnabled(false);

                    file = fileService.save(file);

                    customer.setDriverLicenseFileId(file.getFileId());
                    customerRepository.save(customer);

                    FileApproved fileApproved = new FileApproved();
                    fileApproved.setFileType(FileType.DRIVER_LICENSE);
                    fileApproved.setFileTable(FileTable.CUSTOMER);
                    fileApproved.setFileId(file.getFileId());
                    fileApproved.setCreatedBy(localUser.getUser().getUserId());
                    fileApproved.setCreatedDate(Instant.now());
                    fileApproved.setEnabled(true);

                    fileApprovedService.save(fileApproved);
                });
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        return Optional.of(customerRepository.save(customer));
    }


    @Override
    public Optional<Customer> update(UUID id, Customer customer) {
        return findById(id)
                .map(customerRepository::save);
    }

    @Override
    public void delete(UUID id) {
        findById(id)
                .ifPresent(customerRepository::delete);
    }
}