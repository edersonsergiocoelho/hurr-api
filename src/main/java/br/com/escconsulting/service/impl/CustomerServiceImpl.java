package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.enumeration.CustomerType;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.repository.CustomerRepository;
import br.com.escconsulting.service.*;
import br.com.escconsulting.util.RandomCodeGenerator;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final EmailService emailService;

    private final FileService fileService;

    private final FileApprovedService fileApprovedService;

    private final TwilioService twilioService;

    private final WhatsAppService whatsAppService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService, FileService fileService, FileApprovedService fileApprovedService, TwilioService twilioService, WhatsAppService whatsAppService) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
        this.fileService = fileService;
        this.fileApprovedService = fileApprovedService;
        this.twilioService = twilioService;
        this.whatsAppService = whatsAppService;
    }

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

    public Optional<Customer> emailVerificationCode(Customer customer) {
        Customer savedCustomer = customerRepository.findByEmail(customer.getEmail())
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
                });

        if (savedCustomer.getEmailVerificationCode() != null) {
            emailService.sendEmailVerificationCode(savedCustomer);
            return Optional.of(savedCustomer);
        } else {
            return Optional.empty();
        }
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
        String code = RandomCodeGenerator.generateCode(6).toUpperCase();
        String message = "O seu código de verificação para Hurr é: " + code;

        return customerRepository.findByEmail(customer.getEmail())
                .map(customerToUpdate -> {
                    customerToUpdate.setDdiPhone(customer.getDdiPhone());
                    customerToUpdate.setPhone(customer.getPhone());
                    customerToUpdate.setPhoneVerificationCode(code);
                    return customerRepository.save(customerToUpdate);
                })
                .flatMap(customerUpdated -> Optional.ofNullable(twilioService.sendSMS(customer.getDdiPhone() + customer.getPhone(), message)));
    }

    @Override
    public Optional<Customer> phoneVerificationCodeWhatsApp(Customer customer) {
        String code = RandomCodeGenerator.generateCode(6).toUpperCase();
        String message = "O seu código de verificação para Hurr é: " + code;

        return customerRepository.findByEmail(customer.getEmail())
                .map(customerToUpdate -> {
                    customerToUpdate.setDdiPhone(customer.getDdiPhone());
                    customerToUpdate.setPhone(customer.getPhone());
                    customerToUpdate.setPhoneVerificationCode(code);
                    return customerRepository.save(customerToUpdate);
                })
                .map(savedCustomer -> {
                    try {
                        whatsAppService.sendTemplateMessage(savedCustomer.getDdiPhone() + savedCustomer.getPhone(), message);
                    } catch (URISyntaxException | IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return savedCustomer;
                });
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
    public Optional<FileApproved> uploadIdentityNumber(LocalUser localUser, MultipartFile[] files) throws IOException {
        return customerRepository.findByEmail(localUser.getUser().getEmail())
                .flatMap(customer -> {
                    MultipartFile multipartFile = Arrays.stream(files).findFirst().orElseThrow(() -> new RuntimeException("No files found"));

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

                    file = fileService.save(file).orElseThrow(() -> new RuntimeException("Failed to save file"));

                    customer.setIdentityNumberFileId(file.getFileId());
                    customerRepository.save(customer);

                    FileApproved fileApproved = new FileApproved();
                    fileApproved.setFileType(FileType.IDENTITY_NUMBER);
                    fileApproved.setFileTable(FileTable.CUSTOMER);
                    fileApproved.setCustomerId(customer.getCustomerId());
                    fileApproved.setFileId(file.getFileId());
                    fileApproved.setCreatedBy(localUser.getUser().getUserId());
                    fileApproved.setCreatedDate(Instant.now());
                    fileApproved.setEnabled(true);

                    return fileApprovedService.save(fileApproved);
                });
    }

    @Override
    public Optional<FileApproved> uploadDriverLicense(LocalUser localUser, MultipartFile[] files) throws IOException {
        return customerRepository.findByEmail(localUser.getUser().getEmail())
                .flatMap(customer -> {
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

                    file = fileService.save(file).get();

                    customer.setDriverLicenseFileId(file.getFileId());
                    customerRepository.save(customer);

                    FileApproved fileApproved = new FileApproved();
                    fileApproved.setFileType(FileType.DRIVER_LICENSE);
                    fileApproved.setFileTable(FileTable.CUSTOMER);
                    fileApproved.setCustomerId(customer.getCustomerId());
                    fileApproved.setFileId(file.getFileId());
                    fileApproved.setCreatedBy(localUser.getUser().getUserId());
                    fileApproved.setCreatedDate(Instant.now());
                    fileApproved.setEnabled(true);

                    return fileApprovedService.save(fileApproved);
                });
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        return Optional.of(customerRepository.save(customer));
    }


    @Override
    public Optional<Customer> update(UUID id, Customer customer) {
        return findById(id)
                .map(existingCustomer -> {

                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setDdiPhone(customer.getDdiPhone());
                    existingCustomer.setPhone(customer.getPhone());

                    existingCustomer.setModifiedDate(Instant.now());

                    existingCustomer.setDateOfBirth(customer.getDateOfBirth());
                    existingCustomer.setCpf(customer.getCpf());
                    existingCustomer.setIdentityNumber(customer.getIdentityNumber());
                    existingCustomer.setIdentityNumberIssuingBody(customer.getIdentityNumberIssuingBody());
                    existingCustomer.setIdentityNumberIssuingBodyUF(customer.getIdentityNumberIssuingBodyUF());

                    existingCustomer.setDriverLicenseRegistrationNumber(customer.getDriverLicenseRegistrationNumber());
                    existingCustomer.setDriverLicenseCategory(customer.getDriverLicenseCategory());
                    existingCustomer.setDriverLicenseFirstLicenseDate(customer.getDriverLicenseFirstLicenseDate());
                    existingCustomer.setDriverLicenseExpirationDate(customer.getDriverLicenseExpirationDate());
                    existingCustomer.setDriverLicenseIssueDate(customer.getDriverLicenseIssueDate());
                    existingCustomer.setDriverLicenseIssueUF(customer.getDriverLicenseIssueUF());

                    existingCustomer.setIdentityNumberValidated(customer.isIdentityNumberValidated());
                    existingCustomer.setIdentityNumberFileId(customer.getIdentityNumberFileId());

                    existingCustomer.setDriverLicenseValidated(customer.isDriverLicenseValidated());
                    existingCustomer.setDriverLicenseFileId(customer.getDriverLicenseFileId());

                    return customerRepository.save(existingCustomer);
                });
    }

    @Override
    public void delete(UUID id) {
        findById(id)
                .ifPresent(customerRepository::delete);
    }
}