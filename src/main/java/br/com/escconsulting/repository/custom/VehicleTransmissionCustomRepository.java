package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionSearchDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleTransmissionCustomRepository extends JpaRepository<VehicleTransmission, UUID> {

    Page<VehicleTransmissionDTO> searchPage(VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO, Pageable pageable);

    Long countSearchPage(VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO);
}