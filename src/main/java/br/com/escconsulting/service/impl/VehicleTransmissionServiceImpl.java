package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionSearchDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import br.com.escconsulting.repository.VehicleTransmissionRepository;
import br.com.escconsulting.repository.custom.VehicleTransmissionCustomRepository;
import br.com.escconsulting.service.VehicleTransmissionService;
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
public class VehicleTransmissionServiceImpl implements VehicleTransmissionService {

    private final VehicleTransmissionRepository vehicleTransmissionRepository;

    private final VehicleTransmissionCustomRepository vehicleTransmissionCustomRepository;

    @Transactional
    @Override
    public Optional<VehicleTransmission> findById(UUID vehicleTransmissionId) {

        return Optional.ofNullable(vehicleTransmissionRepository.findById(vehicleTransmissionId)
                .orElseThrow(() -> new RuntimeException("VehicleTransmission not found with vehicleTransmissionId: " + vehicleTransmissionId)));
    }

    @Transactional
    @Override
    public List<VehicleTransmission> findAll() {
        return vehicleTransmissionRepository.findAll();
    }

    @Transactional
    @Override
    public Page<VehicleTransmissionDTO> searchPage(LocalUser localUser, VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO, Pageable pageable) {
        return vehicleTransmissionCustomRepository.searchPage(vehicleTransmissionSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<VehicleTransmission> save(VehicleTransmission vehicleTransmission) {

        vehicleTransmission.setCreatedDate(Instant.now());
        vehicleTransmission.setEnabled(Boolean.TRUE);

        return Optional.of(vehicleTransmissionRepository.save(vehicleTransmission));
    }

    @Transactional
    @Override
    public Optional<VehicleTransmission> update(UUID vehicleTransmissionId, VehicleTransmission vehicleTransmission) {
        return findById(vehicleTransmissionId)
                .map(existingVehicleTransmission -> {

                    existingVehicleTransmission.setEnabled(vehicleTransmission.getEnabled());
                    existingVehicleTransmission.setModifiedDate(Instant.now());

                    return vehicleTransmissionRepository.save(existingVehicleTransmission);
                });
    }

    @Transactional
    @Override
    public void delete(UUID vehicleTransmissionId) {
        findById(vehicleTransmissionId).ifPresent(vehicleTransmissionRepository::delete);
    }
}