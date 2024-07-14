package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeSearchDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import br.com.escconsulting.repository.VehicleFuelTypeRepository;
import br.com.escconsulting.repository.custom.VehicleFuelTypeCustomRepository;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.PaymentStatusService;
import br.com.escconsulting.service.VehicleFuelTypeService;
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
public class VehicleFuelTypeServiceImpl implements VehicleFuelTypeService {

    // Service's
    private final EmailService emailService;

    private final PaymentStatusService paymentStatusService;

    // Repository's
    private final VehicleFuelTypeRepository vehicleFuelTypeRepository;

    private final VehicleFuelTypeCustomRepository vehicleFuelTypeCustomRepository;

    @Transactional
    @Override
    public Optional<VehicleFuelType> findById(UUID vehicleFuelTypeId) {

        return Optional.ofNullable(vehicleFuelTypeRepository.findById(vehicleFuelTypeId)
                .orElseThrow(() -> new RuntimeException("VehicleFuelType not found with vehicleFuelTypeId: " + vehicleFuelTypeId)));
    }

    @Transactional
    @Override
    public List<VehicleFuelType> findAll() {
        return vehicleFuelTypeRepository.findAll();
    }

    @Transactional
    @Override
    public Page<VehicleFuelTypeDTO> searchPage(LocalUser localUser, VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO, Pageable pageable) {
        return vehicleFuelTypeCustomRepository.searchPage(vehicleFuelTypeSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<VehicleFuelType> save(VehicleFuelType vehicleFuelType) {

        vehicleFuelType.setCreatedDate(Instant.now());
        vehicleFuelType.setEnabled(Boolean.TRUE);

        return Optional.of(vehicleFuelTypeRepository.save(vehicleFuelType));
    }

    @Transactional
    @Override
    public Optional<VehicleFuelType> update(UUID vehicleFuelTypeId, VehicleFuelType vehicleFuelType) {
        return findById(vehicleFuelTypeId)
                .map(existingVehicleFuelType -> {

                    existingVehicleFuelType.setEnabled(vehicleFuelType.getEnabled());
                    existingVehicleFuelType.setModifiedDate(Instant.now());

                    return vehicleFuelTypeRepository.save(existingVehicleFuelType);
                });
    }

    @Transactional
    @Override
    public void delete(UUID vehicleFuelTypeId) {
        findById(vehicleFuelTypeId).ifPresent(vehicleFuelTypeRepository::delete);
    }
}