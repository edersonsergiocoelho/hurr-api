package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.repository.CustomerVehicleAddressRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleAddressCustomRepository;
import br.com.escconsulting.service.AddressAddressTypeService;
import br.com.escconsulting.service.AddressService;
import br.com.escconsulting.service.CustomerVehicleAddressService;
import jakarta.persistence.EntityNotFoundException;
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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleAddressServiceImpl implements CustomerVehicleAddressService {

    // Service's
    private final AddressService addressService;

    private final AddressAddressTypeService addressAddressTypeService;

    // Repository's
    private final CustomerVehicleAddressRepository customerVehicleAddressRepository;

    private final CustomerVehicleAddressCustomRepository customerVehicleAddressCustomRepository;

    @Transactional
    @Override
    public CustomerVehicleAddress findById(UUID id) {
        return customerVehicleAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Vehicle Address not found with id: " + id));
    }

    @Transactional
    @Override
    public List<CustomerVehicleAddress> findAll() {
        return customerVehicleAddressRepository.findAll();
    }

    @Transactional
    @Override
    public List<CustomerVehicleAddress> findAllByCustomerVehicleId(UUID customerVehicleId) {
        return customerVehicleAddressRepository.findAllByCustomerVehicleId(customerVehicleId);
    }

    @Transactional
    @Override
    public List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(UUID customerVehicleId, String addressTypeName) {
        return customerVehicleAddressRepository.findAllByCustomerVehicleIdAndAddressType(customerVehicleId, addressTypeName);
    }

    @Transactional
    @Override
    public Page<CustomerVehicleAddressDTO> searchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO, Pageable pageable) {
        return customerVehicleAddressCustomRepository.searchPage(customerVehicleAddressSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleAddress> save(CustomerVehicleAddress customerVehicleAddress) {
        return Optional.of(customerVehicleAddressRepository.save(customerVehicleAddress));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleAddress> saveAddress(CustomerVehicleAddressSaveAddressDTO customerVehicleAddressSaveAddressDTO) {

        Optional<Address> optionalAddress = addressService.save(customerVehicleAddressSaveAddressDTO.getAddress());

        if (optionalAddress.isPresent()) {

            Address savedAddress = optionalAddress.get();

            for (AddressType addressType : customerVehicleAddressSaveAddressDTO.getAddressTypes()) {

                AddressAddressType addressAddressType = new AddressAddressType();

                addressAddressType.setAddress(savedAddress);
                addressAddressType.setAddressType(addressType);

                AddressAddressTypeId addressAddressTypeId = new AddressAddressTypeId();
                addressAddressTypeId.setAddressId(savedAddress.getAddressId());
                addressAddressTypeId.setAddressTypeId(addressType.getAddressTypeId());

                addressAddressType.setId(addressAddressTypeId);

                addressAddressTypeService.save(addressAddressType);
            }

            CustomerVehicleAddress customerVehicleAddress = new CustomerVehicleAddress();
            customerVehicleAddress.setAddress(savedAddress);
            customerVehicleAddress.setCustomerVehicle(customerVehicleAddressSaveAddressDTO.getCustomerVehicle());

            return this.save(customerVehicleAddress);

        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public CustomerVehicleAddress update(UUID id, CustomerVehicleAddress customerVehicleAddress) {
        CustomerVehicleAddress existingCustomerVehicleAddress = findById(id);

        return customerVehicleAddressRepository.save(existingCustomerVehicleAddress);
    }

    @Transactional
    @Override
    public CustomerVehicleAddress updateAddress(UUID customerVehicleAddressId, CustomerVehicleAddressSaveAddressDTO customerVehicleAddressSaveAddressDTO) {

        CustomerVehicleAddress existingCustomerVehicleAddress = findById(customerVehicleAddressId);

        if (existingCustomerVehicleAddress != null) {

            existingCustomerVehicleAddress.setCustomerVehicle(customerVehicleAddressSaveAddressDTO.getCustomerVehicle());

            Address updatedAddress = customerVehicleAddressSaveAddressDTO.getAddress();

            if (updatedAddress != null) {

                existingCustomerVehicleAddress.setAddress(updatedAddress);

                addressService.save(updatedAddress);

                addressAddressTypeService.deleteAllByAddressId(updatedAddress.getAddressId());

                for (AddressType addressType : customerVehicleAddressSaveAddressDTO.getAddressTypes()) {
                    AddressAddressType addressAddressType = new AddressAddressType();
                    addressAddressType.setAddress(updatedAddress);
                    addressAddressType.setAddressType(addressType);

                    AddressAddressTypeId addressAddressTypeId = new AddressAddressTypeId();
                    addressAddressTypeId.setAddressId(updatedAddress.getAddressId());
                    addressAddressTypeId.setAddressTypeId(addressType.getAddressTypeId());

                    addressAddressType.setId(addressAddressTypeId);

                    addressAddressTypeService.save(addressAddressType);
                }
            }

            existingCustomerVehicleAddress.setModifiedDate(Instant.now());

            return customerVehicleAddressRepository.save(existingCustomerVehicleAddress);
        } else {
            throw new EntityNotFoundException("CustomerVehicleAddress not found with customerVehicleAddressId: " + customerVehicleAddressId);
        }
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        CustomerVehicleAddress review = findById(id);
        customerVehicleAddressRepository.delete(review);
    }
}