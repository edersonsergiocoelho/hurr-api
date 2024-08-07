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
import jakarta.persistence.criteria.*;
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

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        Fetch<CustomerVehicle, VehicleColor> vehicleColorFetch = root.fetch("vehicleColor");
        Fetch<CustomerVehicle, VehicleFuelType> vehicleFuelTypeFetch = root.fetch("vehicleFuelType");
        Fetch<CustomerVehicle, VehicleTransmission> vehicleTransmissionFetch = root.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleAddressFetch = root.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = customerVehicleAddressFetch.fetch("address", JoinType.LEFT);

        Fetch<Address, AddressAddressType> addressAddressTypeFetch = addressFetch.fetch("addressTypes", JoinType.LEFT);
        Fetch<AddressAddressType, AddressType> addressTypeFetch = addressAddressTypeFetch.fetch("addressType", JoinType.LEFT);

        Fetch<Address, Country> countryFetch = addressFetch.fetch("country", JoinType.LEFT);
        Fetch<Address, City> cityFetch = addressFetch.fetch("city", JoinType.LEFT);
        Fetch<Address, State> stateFetch = addressFetch.fetch("state", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        spec = cb.and(spec, cb.equal(root.get("customerVehicleId"), customerVehicleId));

        cq.where(spec);

        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
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
    public List<CustomerVehicle> search(CustomerVehicleSearchDTO customerVehicleSearchDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerVehicle> cq = cb.createQuery(CustomerVehicle.class);
        Root<CustomerVehicle> root = cq.from(CustomerVehicle.class);

        Fetch<CustomerVehicle, Vehicle> vehicleFetch = root.fetch("vehicle");
        Fetch<Vehicle, VehicleBrand> vehicleBrandFetch = vehicleFetch.fetch("vehicleBrand");

        Fetch<CustomerVehicle, VehicleModel> vehicleModelFetch = root.fetch("vehicleModel");
        Fetch<VehicleModel, VehicleCategory> vehicleCategoryFetch = vehicleModelFetch.fetch("vehicleCategory");

        Fetch<CustomerVehicle, VehicleColor> vehicleColorFetch = root.fetch("vehicleColor");
        Fetch<CustomerVehicle, VehicleFuelType> vehicleFuelTypeFetch = root.fetch("vehicleFuelType");
        Fetch<CustomerVehicle, VehicleTransmission> vehicleTransmissionFetch = root.fetch("vehicleTransmission");

        Fetch<CustomerVehicle, CustomerVehicleAddress> customerVehicleAddressFetch = root.fetch("addresses", JoinType.LEFT);
        Fetch<CustomerVehicleAddress, Address> addressFetch = customerVehicleAddressFetch.fetch("address", JoinType.LEFT);

        Fetch<Address, AddressAddressType> addressAddressTypeFetch = addressFetch.fetch("addressTypes", JoinType.LEFT);
        Fetch<AddressAddressType, AddressType> addressTypeFetch = addressAddressTypeFetch.fetch("addressType", JoinType.LEFT);

        Fetch<Address, Country> countryFetch = addressFetch.fetch("country", JoinType.LEFT);
        Fetch<Address, City> cityFetch = addressFetch.fetch("city", JoinType.LEFT);
        Fetch<Address, State> stateFetch = addressFetch.fetch("state", JoinType.LEFT);

        Predicate spec = cb.conjunction();

        spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("addressTypes").get("addressType").get("addressTypeName"), "VEHICLE"));

        if (customerVehicleSearchDTO.getVehicleId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleId"), customerVehicleSearchDTO.getVehicleId()));
        }

        if (customerVehicleSearchDTO.getVehicleModelId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleModelId"), customerVehicleSearchDTO.getVehicleModelId()));
        }

        if (customerVehicleSearchDTO.getVehicleBrandId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicle").get("vehicleBrand").get("vehicleBrandId"), customerVehicleSearchDTO.getVehicleBrandId()));
        }

        if (customerVehicleSearchDTO.getVehicleCategoryId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleModel").get("vehicleCategory").get("vehicleCategoryId"), customerVehicleSearchDTO.getVehicleCategoryId()));
        }

        if (customerVehicleSearchDTO.getVehicleColorId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleColor").get("vehicleColorId"), customerVehicleSearchDTO.getVehicleColorId()));
        }

        if (customerVehicleSearchDTO.getVehicleFuelTypeId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleFuelType").get("vehicleFuelTypeId"), customerVehicleSearchDTO.getVehicleFuelTypeId()));
        }

        if (customerVehicleSearchDTO.getVehicleTransmissionId() != null) {
            spec = cb.and(spec, cb.equal(root.get("vehicleTransmission").get("vehicleTransmissionId"), customerVehicleSearchDTO.getVehicleTransmissionId()));
        }

        if (customerVehicleSearchDTO.getCountryName() != null && !customerVehicleSearchDTO.getCountryName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("country").get("countryName"), customerVehicleSearchDTO.getCountryName()));
        }

        if (customerVehicleSearchDTO.getStateName() != null && !customerVehicleSearchDTO.getStateName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("state").get("stateName"), customerVehicleSearchDTO.getStateName()));
        }

        if (customerVehicleSearchDTO.getStateName() != null && !customerVehicleSearchDTO.getStateName().isEmpty()) {
            spec = cb.and(spec, cb.equal(root.get("addresses").get("address").get("city").get("cityName"), customerVehicleSearchDTO.getCityName()));
        }

        cq.where(spec);

        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleDTO> searchPage(LocalUser localUser, CustomerVehicleSearchDTO customerVehicleSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleCustomRepository.searchPage(customerVehicleSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
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