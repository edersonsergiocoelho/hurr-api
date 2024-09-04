package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSaveDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.mapper.CustomerVehicleMapper;
import br.com.escconsulting.repository.CustomerVehicleRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleCustomRepository;
import br.com.escconsulting.service.*;
import br.com.escconsulting.util.RandomCodeGenerator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleServiceImpl implements CustomerVehicleService {

    // Service's
    private final AddressService addressService;

    private final AddressTypeService addressTypeService;

    private final AddressAddressTypeService addressAddressTypeService;

    private final CustomerService customerService;

    private final CustomerVehicleAddressService customerVehicleAddressService;

    private final CustomerVehicleApprovedService customerVehicleApprovedService;

    private final CustomerVehicleFilePhotoService customerVehicleFilePhotoService;

    private final CustomerVehicleFileInsuranceService customerVehicleFileInsuranceService;

    private final EmailService emailService;

    // Repository's
    private final CustomerVehicleRepository customerVehicleRepository;

    private final CustomerVehicleCustomRepository customerVehicleCustomRepository;

    private final EntityManager entityManager;

    @Transactional
    @Override
    public Optional<CustomerVehicle> findById(UUID customerVehicleId) {
        return Optional.ofNullable(customerVehicleRepository.findById(customerVehicleId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicle not found with customerVehicleId: " + customerVehicleId)));
    }

    @Transactional
    @Override
    public boolean existsByCode(String code) {
        return customerVehicleRepository.existsByCode(code);
    }

    @Transactional
    @Override
    public List<CustomerVehicle> findAll() {
        return customerVehicleRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleDTO> searchPage(CustomerVehicleSearchDTO customerVehicleSearchDTO, Pageable pageable) {
        return customerVehicleCustomRepository.searchPage(customerVehicleSearchDTO, pageable);
    }

    @Transactional
    public Optional<CustomerVehicle> save(LocalUser localUser, CustomerVehicleSaveDTO customerVehicleSaveDTO) {

        return Optional.ofNullable(customerService.findByEmail(localUser.getUsername())
                .flatMap(customer -> {

                    // CustomerVehicle
                    CustomerVehicle customerVehicleSave = customerVehicleSaveDTO.getCustomerVehicle();

                    customerVehicleSave.setCustomer(customer);

                    String code;
                    do {
                        String countryCode = customerVehicleSaveDTO.getAddress().getCountry().getCountryCode();
                        Integer yearOfTheCar = customerVehicleSaveDTO.getCustomerVehicle().getYearOfTheCar();
                        String generateCode = RandomCodeGenerator.generateCode(6).toUpperCase();
                        code = countryCode + "-" + yearOfTheCar + "-" + generateCode;
                    } while (customerVehicleRepository.existsByCode(code));

                    customerVehicleSave.setCode(code);

                    customerVehicleSave.setCreatedDate(Instant.now());
                    customerVehicleSave.setEnabled(true);

                    final CustomerVehicle customerVehicleSaveFinal = customerVehicleCustomRepository.save(customerVehicleSave);

                    // Address
                    Address address = customerVehicleSaveDTO.getAddress();

                    address.setCreatedDate(Instant.now());
                    address.setEnabled(true);

                    address = addressService.save(address).get();

                    // AddressAddressType
                    AddressType addressType = addressTypeService.findByAddressTypeName("VEHICLE").get();

                    AddressAddressType addressAddressType = new AddressAddressType();

                    AddressAddressTypeId id = new AddressAddressTypeId();
                    id.setAddressId(address.getAddressId());
                    id.setAddressTypeId(addressType.getAddressTypeId());

                    addressAddressType.setId(id);

                    addressAddressType.setAddress(address);
                    addressAddressType.setAddressType(addressTypeService.findByAddressTypeName("VEHICLE").get());

                    addressAddressType.setCreatedDate(Instant.now());
                    addressAddressType.setEnabled(true);

                    addressAddressTypeService.save(addressAddressType);

                    // CustomerVehicleAddress
                    CustomerVehicleAddress customerVehicleAddress = new CustomerVehicleAddress();

                    customerVehicleAddress.setAddress(address);
                    customerVehicleAddress.setCustomerVehicle(customerVehicleSave);

                    customerVehicleAddress.setCreatedDate(Instant.now());
                    customerVehicleAddress.setEnabled(true);

                    customerVehicleAddressService.save(customerVehicleAddress);

                    // CustomerVehicleFilePhoto
                    List<CustomerVehicleFilePhoto> savedPhotos = customerVehicleSaveDTO.getCustomerVehicleFilePhotos().stream()
                            .peek(photo -> {
                                photo.setCustomerVehicle(customerVehicleSaveFinal);
                            })
                            .collect(Collectors.toList());

                    customerVehicleFilePhotoService.saveAll(savedPhotos);

                    // CustomerVehicleFileInsurance
                    List<CustomerVehicleFileInsurance> savedInsurances = customerVehicleSaveDTO.getCustomerVehicleFileInsurances().stream()
                            .peek(insurance -> {
                                insurance.setCustomerVehicle(customerVehicleSaveFinal);
                            })
                            .collect(Collectors.toList());

                    customerVehicleFileInsuranceService.saveAll(savedInsurances);

                    // CustomerVehicleApproved
                    CustomerVehicleApproved customerVehicleApproved = new CustomerVehicleApproved();

                    customerVehicleApproved.setCustomerVehicle(customerVehicleSave);

                    customerVehicleApproved.setCreatedBy(localUser.getUser().getUserId());
                    customerVehicleApproved.setCreatedDate(Instant.now());
                    customerVehicleApproved.setEnabled(Boolean.TRUE);

                    customerVehicleApprovedService.save(customerVehicleApproved);

                    // Email
                    emailService.sendCustomerVehicleCreated(customerVehicleSave);

                    return Optional.of(customerVehicleSave);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername())));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicle> update(UUID customerVehicleId, CustomerVehicle customerVehicle) {
        return findById(customerVehicleId)
                .map(existingCustomerVehicle -> {

                    CustomerVehicleMapper.INSTANCE.update(customerVehicle, existingCustomerVehicle);

                    existingCustomerVehicle.setModifiedDate(Instant.now());

                    return customerVehicleRepository.save(existingCustomerVehicle);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleId) {
        findById(customerVehicleId).ifPresent(customerVehicleRepository::delete);
    }
}