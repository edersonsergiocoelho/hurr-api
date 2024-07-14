package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionSearchDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleTransmissionService {

    Optional<VehicleTransmission> findById(UUID vehicleTransmissionId);

    List<VehicleTransmission> findAll();

    Page<VehicleTransmissionDTO> searchPage(LocalUser localUser, VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO, Pageable pageable);

    Optional<VehicleTransmission> save(VehicleTransmission vehicleTransmission);

    Optional<VehicleTransmission> update(UUID vehicleTransmissionId, VehicleTransmission vehicleTransmission);

    void delete(UUID vehicleTransmissionId);
}