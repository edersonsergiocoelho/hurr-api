package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorSearchDTO;
import br.com.escconsulting.entity.VehicleColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleColorCustomRepository extends JpaRepository<VehicleColor, UUID> {

    Page<VehicleColorDTO> searchPage(VehicleColorSearchDTO vehicleColorSearchDTO, Pageable pageable);

    Long countSearchPage(VehicleColorSearchDTO vehicleColorSearchDTO);
}