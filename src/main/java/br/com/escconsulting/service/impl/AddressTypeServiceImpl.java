package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.address.type.AddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressType;
import br.com.escconsulting.repository.AddressTypeRepository;
import br.com.escconsulting.repository.custom.AddressTypeCustomRepository;
import br.com.escconsulting.service.AddressTypeService;
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
public class AddressTypeServiceImpl implements AddressTypeService {

    private final AddressTypeRepository addressTypeRepository;

    private final AddressTypeCustomRepository addressTypeCustomRepository;

    @Override
    @Transactional
    public Optional<AddressType> findById(UUID addressTypeId) {

        return Optional.ofNullable(addressTypeRepository.findById(addressTypeId)
                .orElseThrow(() -> new RuntimeException("AddressType not found with addressTypeId: " + addressTypeId)));
    }

    @Override
    public Optional<AddressType> findByAddressTypeName(String addressTypeName) {
        return addressTypeRepository.findByAddressTypeName(addressTypeName);
    }

    @Override
    @Transactional
    public List<AddressType> findAll() {
        return addressTypeRepository.findAll();
    }

    @Override
    @Transactional
    public Page<AddressType> searchPage(AddressTypeSearchDTO addressTypeSearchDTO, Pageable pageable) {
        return addressTypeCustomRepository.searchPage(addressTypeSearchDTO, pageable);
    }

    @Override
    @Transactional
    public Optional<AddressType> save(AddressType addressType) {

        addressType.setCreatedDate(Instant.now());
        addressType.setEnabled(Boolean.TRUE);

        return Optional.of(addressTypeRepository.save(addressType));
    }

    @Override
    @Transactional
    public Optional<AddressType> update(UUID addressTypeId, AddressType addressType) {
        return findById(addressTypeId)
                .map(existingAddressType -> {

                    existingAddressType.setEnabled(addressType.getEnabled());
                    existingAddressType.setModifiedDate(Instant.now());

                    return addressTypeRepository.save(existingAddressType);

                });
    }

    @Override
    @Transactional
    public void delete(UUID addressTypeId) {
        findById(addressTypeId).ifPresent(addressTypeRepository::delete);
    }
}