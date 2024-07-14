package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeSearchDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleFuelTypeCustomRepository extends JpaRepository<VehicleFuelType, UUID> {

    Page<VehicleFuelTypeDTO> searchPage(VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO, Pageable pageable);

    Long countSearchPage(VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO);
}