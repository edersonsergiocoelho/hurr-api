package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorSearchDTO;
import br.com.escconsulting.entity.VehicleColor;
import br.com.escconsulting.repository.VehicleColorRepository;
import br.com.escconsulting.repository.custom.VehicleColorCustomRepository;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.VehicleColorService;
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
public class VehicleColorServiceImpl implements VehicleColorService {

    // Service's
    private final EmailService emailService;

    // Repository's
    private final VehicleColorRepository vehicleColorRepository;

    private final VehicleColorCustomRepository vehicleColorCustomRepository;

    @Transactional
    @Override
    public Optional<VehicleColor> findById(UUID vehicleColorId) {

        return Optional.ofNullable(vehicleColorRepository.findById(vehicleColorId)
                .orElseThrow(() -> new RuntimeException("VehicleColor not found with vehicleColorId: " + vehicleColorId)));
    }

    @Transactional
    @Override
    public List<VehicleColor> findAll() {
        return vehicleColorRepository.findAll();
    }

    @Transactional
    @Override
    public Page<VehicleColorDTO> searchPage(LocalUser localUser, VehicleColorSearchDTO vehicleColorSearchDTO, Pageable pageable) {
        return vehicleColorCustomRepository.searchPage(vehicleColorSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<VehicleColor> save(VehicleColor vehicleColor) {

        vehicleColor.setCreatedDate(Instant.now());
        vehicleColor.setEnabled(Boolean.TRUE);

        return Optional.of(vehicleColorRepository.save(vehicleColor));
    }

    @Transactional
    @Override
    public Optional<VehicleColor> update(UUID vehicleColorId, VehicleColor vehicleColor) {
        return findById(vehicleColorId)
                .map(existingVehicleColor -> {

                    existingVehicleColor.setEnabled(vehicleColor.getEnabled());
                    existingVehicleColor.setModifiedDate(Instant.now());

                    return vehicleColorRepository.save(existingVehicleColor);
                });
    }

    @Transactional
    @Override
    public void delete(UUID vehicleColorId) {
        findById(vehicleColorId).ifPresent(vehicleColorRepository::delete);
    }
}