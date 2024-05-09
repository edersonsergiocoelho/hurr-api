package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.repository.AddressAddressTypeRepository;
import br.com.escconsulting.repository.custom.AddressAddressTypeCustomRepository;
import br.com.escconsulting.service.AddressAddressTypeService;
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
public class AddressAddressTypeServiceImpl implements AddressAddressTypeService {

    private final AddressAddressTypeRepository addressAddressTypeRepository;

    private final AddressAddressTypeCustomRepository addressAddressTypeCustomRepository;

    @Override
    @Transactional
    public Optional<AddressAddressType> findById(UUID addressAddressTypeId) {

        return Optional.ofNullable(addressAddressTypeRepository.findById(addressAddressTypeId)
                .orElseThrow(() -> new RuntimeException("AddressAddressType not found with addressAddressTypeId: " + addressAddressTypeId)));
    }

    @Override
    @Transactional
    public List<AddressAddressType> findAll() {
        return addressAddressTypeRepository.findAll();
    }

    @Override
    @Transactional
    public Page<AddressAddressType> searchPage(AddressAddressTypeSearchDTO addressAddressTypeSearchDTO, Pageable pageable) {
        return addressAddressTypeCustomRepository.searchPage(addressAddressTypeSearchDTO, pageable);
    }

    @Override
    @Transactional
    public Optional<AddressAddressType> save(AddressAddressType addressAddressType) {

        addressAddressType.setCreatedDate(Instant.now());
        addressAddressType.setEnabled(Boolean.TRUE);

        return Optional.of(addressAddressTypeRepository.save(addressAddressType));
    }

    @Override
    @Transactional
    public Optional<AddressAddressType> update(UUID addressAddressTypeId, AddressAddressType addressAddressType) {
        return findById(addressAddressTypeId)
                .map(existingAddressAddressType -> {

                    existingAddressAddressType.setEnabled(addressAddressType.getEnabled());
                    existingAddressAddressType.setModifiedDate(Instant.now());

                    return addressAddressTypeRepository.save(existingAddressAddressType);

                });
    }

    @Override
    @Transactional
    public void delete(UUID addressAddressTypeId) {
        findById(addressAddressTypeId).ifPresent(addressAddressTypeRepository::delete);
    }
}