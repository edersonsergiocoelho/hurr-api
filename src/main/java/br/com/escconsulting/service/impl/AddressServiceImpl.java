package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.address.AddressSearchDTO;
import br.com.escconsulting.entity.Address;
import br.com.escconsulting.repository.AddressRepository;
import br.com.escconsulting.repository.custom.AddressCustomRepository;
import br.com.escconsulting.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressCustomRepository addressCustomRepository;

    @Override
    @Transactional
    public Optional<Address> findById(UUID addressId) {

        return Optional.ofNullable(addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with addressId: " + addressId)));
    }

    @Override
    @Transactional
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Address> searchPage(AddressSearchDTO addressSearchDTO, Pageable pageable) {
        return addressCustomRepository.searchPage(addressSearchDTO, pageable);
    }

    @Override
    @Transactional
    public Optional<Address> save(Address address) {

        address.setCreatedDate(Instant.now());
        address.setEnabled(Boolean.TRUE);

        return Optional.of(addressRepository.save(address));
    }

    @Override
    @Transactional
    public Optional<Address> update(UUID addressId, Address address) {
        return findById(addressId)
                .map(existingAddress -> {

                    BeanUtils.copyProperties(address, existingAddress);
                    existingAddress.setModifiedDate(Instant.now());

                    return addressRepository.save(existingAddress);
                });
    }

    @Override
    @Transactional
    public void delete(UUID addressId) {
        findById(addressId).ifPresent(addressRepository::delete);
    }
}